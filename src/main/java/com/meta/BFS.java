package com.meta;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class BFS extends Algorithm {
    @Override
    public Solution solve(IntanceSAT intanceSAT, long timeout) {
        ArrayDeque<Solution> open = new ArrayDeque<>(intanceSAT.variables.size());

        Solution first = new Solution(new LinkedHashSet<>());
        first.addNextVar(intanceSAT.variables, open);

        long start = System.currentTimeMillis();
        long end = start + timeout;


        while (!open.isEmpty() && System.currentTimeMillis() <= end) {
            Solution current = open.pop();
            intanceSAT.last = current;
            if (intanceSAT.satisfy(current)) return current;
            else current.addNextVar(intanceSAT.variables, open);
        }
        return null;
    }
}
