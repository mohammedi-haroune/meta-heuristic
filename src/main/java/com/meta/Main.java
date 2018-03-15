package com.meta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) throws IOException {
        Algorithm[] algorithms = {new BFS()};
        List<Path> inputs = Files
                .walk(Paths.get("benchmarks/"))
                .filter(path -> !path.toFile().isDirectory())
                .collect(Collectors.toList());

        long timeout = 10 * 60 * 1000;

        System.out.println("inputs = " + inputs);

        //HashMap<String, InstanceSAT> problems = new HashMap<>();

        for (Algorithm algorithm : algorithms) {
            for (Path input : inputs) {
                InstanceSAT instanceSAT = InstanceSAT.fromCNF(input.toString());
                //problems.put(algorithm.name + "_" + input.toString(), instanceSAT);
                algorithm.solve(instanceSAT, timeout);
                System.out.print("algo_input = " + algorithm.name + "_" + input.toString());
                System.out.println(" pr = " + (double) instanceSAT.numSatisfy(instanceSAT.last) /
                        (double) instanceSAT.clauses.length);
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