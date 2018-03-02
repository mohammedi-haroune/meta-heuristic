package com.meta;

import java.util.LinkedHashSet;
import java.util.Stack;

public class DFS extends Algorithme {
    @Override
    public Solution solve(Problem problem) {
        Solution first = new Solution(new LinkedHashSet<>());
        Stack<Solution> open = new Stack<>();
        for (int var :
                 first.next(problem.variables)) {
            open.push(first.addVar(var));
            open.push(first.addVar(-var));
        }

        while(!open.empty()) {
            Solution current = open.pop();
            if (problem.satisfy(current)) return current;
            else {
                for (int var :
                        current.next(problem.variables)) {
                    open.push(current.addVar(var));
                    open.push(current.addVar(-var));
                }
            }
        }
        return first;
    }
}
