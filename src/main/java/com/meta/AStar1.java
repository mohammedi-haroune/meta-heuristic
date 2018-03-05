package com.meta;

public class AStar1 extends AStar {
    public double h(IntanceSAT intanceSAT, Solution solution) {
        return intanceSAT.distance(solution);
    }

    public double g(IntanceSAT intanceSAT, Solution solution) {
        return solution.size();
    }
}
