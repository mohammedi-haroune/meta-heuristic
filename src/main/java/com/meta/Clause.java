package com.meta;

import java.util.HashSet;

public class Clause {
    private HashSet<Integer> literals;

    public Clause(HashSet<Integer> literals) {
        this.literals = literals;
    }

    public HashSet<Integer> getLiterals() {
        return literals;
    }

    public boolean satisfy(Solution solution) {
        for (int literal :
                literals)
            if (solution.contains(literal)) return true;

        return false;
    }
}
