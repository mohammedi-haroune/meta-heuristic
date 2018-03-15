package com.meta;

import java.util.LinkedHashSet;
import java.util.Stack;

public class DFS extends Algorithm {
    @Override
    public Solution solve(InstanceSAT instanceSAT, long timeout) {
        Stack<Solution> open = new Stack<>();

        Solution first = new Solution(new LinkedHashSet<>());
        first.addNextVar(instanceSAT.variables, open);
        long start = System.currentTimeMillis();
        long end = start + timeout;


        while (!open.empty() && System.currentTimeMillis() <= end) {
            Solution current = open.pop();
            if (instanceSAT.tauxSatisfy(current) > instanceSAT.tauxSatisfy(instanceSAT.last))
                instanceSAT.last = current;

            if (instanceSAT.satisfy(current)) return current;
            else current.addNextVar(instanceSAT.variables, open);
        }
        return null;
    }
}
