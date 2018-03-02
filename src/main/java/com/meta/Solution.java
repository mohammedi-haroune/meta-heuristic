package com.meta;

import scala.Int;
import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    private LinkedHashSet<Integer> literals;

    public Solution(LinkedHashSet<Integer> literals) {
        this.literals = new LinkedHashSet<>(literals);
    }

    public Set<Integer> next(LinkedHashSet<Integer> varialbes) {
        return varialbes.stream().filter(var -> !literals.contains(var) && !literals.contains(-var)).collect(Collectors.toSet());
    }


    public Solution addVar(int var) {
        Solution newSol = new Solution(literals);
        newSol.literals.add(var);
        return newSol;
    }

    public boolean contains(int var) {
        return literals.contains(var);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "literals=" + literals +
                '}';
    }
}
