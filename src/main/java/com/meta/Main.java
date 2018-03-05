package com.meta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) throws IOException {
        Algorithm[] algorithms = {new DFS(), new BFS(), new AStar1()};
        List<Path> inputs = Files
                .walk(Paths.get("benchmarks/"))
                .filter(path -> !path.toFile().isDirectory())
                .limit(1)
                .collect(Collectors.toList());

        long timeout = 5 * 60 * 1000;

        System.out.println("inputs = " + inputs);

        //HashMap<String, IntanceSAT> problems = new HashMap<>();

        for (Algorithm algorithm : algorithms) {
            for (Path input : inputs) {
                IntanceSAT intanceSAT = IntanceSAT.fromCNF(input.toString());
                //problems.put(algorithm.name + "_" + input.toString(), intanceSAT);
                algorithm.solve(intanceSAT, timeout);
                System.out.print("algo_input = " + algorithm.name + "_" + input.toString());
                System.out.println(" pr = " + (double) intanceSAT.numSatisfy(intanceSAT.last) /
                        (double) intanceSAT.clauses.length);
                System.gc();
            }
        }

        /*
        problems.forEach((algo, pr) -> {
            System.out.print("algo_input = " + algo);
            System.out.println(" pr = " + (double) pr.numSatisfy(pr.last) / (double) pr.clauses.length);
        });
         */
    }
}