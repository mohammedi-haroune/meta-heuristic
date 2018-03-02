package com.meta;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS extends Algorithme {
    @Override
    public Solution solve(Problem problem) {
        Solution first = new Solution(new LinkedHashSet<>());
        ArrayDeque<Solution> open = new ArrayDeque<>(problem.variables.size());
        for (int var :
                 first.next(problem.variables)) {
            open.add(first.addVar(var));
            open.add(first.addVar(-var));
        }

        while(!open.isEmpty()) {
            Solution current = open.remove();
            if (problem.satisfy(current)) return current;
            else {
                for (int var :
                        current.next(problem.variables)) {
                    open.add(current.addVar(var));
                    open.add(current.addVar(-var));
                }
            }
        }
        return first;
    }
}
