package com.meta;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        String file = "test.cnf";
        Problem problem = Problem.fromCNF(file);
        BFS dfs = new BFS();
        Solution solution = dfs.solve(problem);
        System.out.println("solution = " + solution);
    }
}
