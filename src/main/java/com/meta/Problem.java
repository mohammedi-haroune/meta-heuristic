package com.meta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem {
    private Clause[] clauses;
    public LinkedHashSet<Integer> variables = new LinkedHashSet<>();

    public Problem(Clause[] clauses) {
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

    public static Problem fromCNF(String file) throws IOException {
        return new Problem(Files
                .readAllLines(Paths.get(file))
                .stream()
                .filter(line -> !line.startsWith("c") && !line.startsWith("p"))
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
}
