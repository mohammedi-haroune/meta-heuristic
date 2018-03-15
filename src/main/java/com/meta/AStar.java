package com.meta;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public abstract class AStar extends Algorithm {
    public abstract double h(InstanceSAT instanceSAT, Solution solution);

    public abstract double g(InstanceSAT instanceSAT, Solution solution);

    @Override
    public Solution solve(InstanceSAT instanceSAT, long timeout) {
        Solution first = new Solution(new LinkedHashSet<>());
        instanceSAT.last = first;
        TreeSet<Solution> open = new TreeSet<>(Comparator.comparingDouble(solution -> g(instanceSAT, solution) + h(instanceSAT, solution)));
        LinkedHashSet<Solution> closed = new LinkedHashSet<>();
        first.addNextVars(instanceSAT.variables, open, closed);


        long start = System.currentTimeMillis();
        long end = start + timeout;


        while (!open.isEmpty() && System.currentTimeMillis() <= end) {
            Solution current = open.pollFirst();
            if (instanceSAT.tauxSatisfy(current) > instanceSAT.tauxSatisfy(instanceSAT.last))
                instanceSAT.last = current;

            closed.add(current);
            if (instanceSAT.satisfy(current)) return current;
            else current.addNextVars(instanceSAT.variables, open, closed);
        }
        return null;
    }
}
