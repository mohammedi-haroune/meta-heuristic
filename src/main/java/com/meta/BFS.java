package com.meta;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class BFS extends Algorithm {
    @Override
    public Solution solve(InstanceSAT instanceSAT, long timeout) {
        ArrayDeque<Solution> open = new ArrayDeque<>(instanceSAT.variables.size());

        Solution first = new Solution(new LinkedHashSet<>());
        first.addNextVar(instanceSAT.variables, open);

        long start = System.currentTimeMillis();
        long end = start + timeout;


        while (!open.isEmpty() && System.currentTimeMillis() <= end) {
            Solution current = open.pop();
            if (instanceSAT.tauxSatisfy(current) > instanceSAT.tauxSatisfy(instanceSAT.last))
                instanceSAT.last = current;

            if (instanceSAT.satisfy(current)) return current;
            else current.addNextVar(instanceSAT.variables, open);
        }
        return null;
    }
}
