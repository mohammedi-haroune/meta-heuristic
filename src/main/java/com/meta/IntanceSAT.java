package com.meta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class IntanceSAT {
    public Clause[] clauses;
    public Solution last;
    public HashSet<Integer> variables = new LinkedHashSet<>();

    private IntanceSAT(Clause[] clauses) {
        this.clauses = clauses;
        for (Clause clause : clauses) {
            variables.addAll(clause.getLiterals().stream().map(Math::abs).collect(Collectors.toSet()));
        }
    }

    public boolean satisfy(Solution solution) {
        for (Clause c :
                clauses)
            if (!c.satisfy(solution)) return false;

        return true;
    }

    public long numSatisfy(Solution solution) {
        return Arrays.stream(clauses).filter(clause -> clause.satisfy(solution)).count();
    }

    public static IntanceSAT fromCNF(String file) throws IOException {
        return new IntanceSAT(Files
                .readAllLines(Paths.get(file))
                .stream()
                .filter(line -> !line.startsWith("c") && !line.startsWith("p")  && !line.startsWith("%") && !line.startsWith("0"))
                .map(line -> {
                    String[] literals = line.split(" ");
                    LinkedHashSet<Integer> l = new LinkedHashSet<>();
                    for (int i = 0; i < literals.length - 1; i++) {
                        l.add(Integer.parseInt(literals[i]));
                    }
                    return new Clause(l);
                })
                .toArray(Clause[]::new));
    }

    @Override
    public String toString() {
        return "IntanceSAT{" +
                "clauses=" + Arrays.toString(clauses) +
                ", variables=" + variables +
                '}';
    }

    public double distance(Solution solution) {
        return clauses.length - numSatisfy(solution);
    }
}
