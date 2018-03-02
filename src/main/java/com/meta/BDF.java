package com.meta;

import java.util.SortedSet;
import java.util.Stack;

public class BDF extends Algorithme {
    @Override
    public Solution solve(Problem problem) {
        Solution solution = new Solution(problem.variables);
        Stack<Integer> open = new Stack<>();
        for (int var :
                solution.next())
            open.push(var);

        while(!open.empty()) {
            int var = open.pop();
            solution.add(var);

        }
        return solution;
    }
}
