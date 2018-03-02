package com.meta;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution extends HashSet<Integer> {
    private int[] varialbes;

    public Solution(int[] varialbes) {
        this.varialbes = varialbes;
    }

    public int[] next() {
        return Arrays.stream(varialbes).filter(var -> !contains(var) && !contains(-var)).toArray();
    }
}
