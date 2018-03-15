package com.meta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class InstanceSAT {
    public Clause[] clauses;
    public Solution last = new Solution();
    public HashSet<Integer> variables = new LinkedHashSet<>();

    private InstanceSAT(Clause[] clauses) {
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

    public double tauxSatisfy(Solution solution) {
        return (double) numSatisfy(solution) / (double) clauses.length;
    }

    public static InstanceSAT fromCNF(String file) throws IOException {
        return new InstanceSAT(Files
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
        return "InstanceSAT{" +
                "clauses=" + Arrays.toString(clauses) +
                ", variables=" + variables +
                '}';
    }

    public double distance(Solution solution) {
        return clauses.length - numSatisfy(solution);
    }
}
