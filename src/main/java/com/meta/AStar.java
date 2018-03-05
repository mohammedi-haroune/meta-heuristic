package com.meta;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public abstract class AStar extends Algorithm {
    public abstract double h(IntanceSAT intanceSAT, Solution solution);

    public abstract double g(IntanceSAT intanceSAT, Solution solution);

    @Override
    public Solution solve(IntanceSAT intanceSAT, long timeout) {
        Solution first = new Solution(new LinkedHashSet<>());
        intanceSAT.last = first;
        TreeSet<Solution> open = new TreeSet<>(Comparator.comparingDouble(solution -> g(intanceSAT, solution) + h(intanceSAT, solution)));
        LinkedHashSet<Solution> closed = new LinkedHashSet<>();
        first.addNextVars(intanceSAT.variables, open, closed);


        long start = System.currentTimeMillis();
        long end = start + timeout;


        while (!open.isEmpty() && System.currentTimeMillis() <= end) {
            Solution current = open.pollFirst();
            intanceSAT.last = current;
            closed.add(current);
            if (intanceSAT.satisfy(current)) return current;
            else current.addNextVars(intanceSAT.variables, open, closed);
        }
        return null;
    }
}
