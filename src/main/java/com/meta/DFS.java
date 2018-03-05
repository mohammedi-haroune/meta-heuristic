package com.meta;

import java.util.LinkedHashSet;
import java.util.Stack;

public class DFS extends Algorithm {
    @Override
    public Solution solve(IntanceSAT intanceSAT, long timeout) {
        Stack<Solution> open = new Stack<>();

        Solution first = new Solution(new LinkedHashSet<>());
        first.addNextVar(intanceSAT.variables, open);
        long start = System.currentTimeMillis();
        long end = start + timeout;


        while (!open.empty() && System.currentTimeMillis() <= end) {
            Solution current = open.pop();
            intanceSAT.last = current;
            if (intanceSAT.satisfy(current)) return current;
            else current.addNextVar(intanceSAT.variables, open);
        }
        return null;
    }
}
