package com.meta;

public class AStar1 extends AStar {
    public double h(InstanceSAT instanceSAT, Solution solution) {
        return instanceSAT.distance(solution);
    }

    public double g(InstanceSAT instanceSAT, Solution solution) {
        return solution.size();
    }
}
