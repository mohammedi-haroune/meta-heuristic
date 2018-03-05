package com.meta;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    private HashSet<Integer> literals;

    public Solution(HashSet<Integer> literals) {
        this.literals = new HashSet<>(literals);
    }

    private Set<Integer> nextVars(HashSet<Integer> varialbes) {
        return varialbes
                .stream()
                .filter(var -> !literals.contains(var) && !literals.contains(-var))
                .collect(Collectors.toSet());
    }

    private Optional<Integer> nextVar(HashSet<Integer> varialbes) {
        return varialbes
                .stream()
                .filter(var -> !literals.contains(var) && !literals.contains(-var))
                .findFirst();
    }

    public void addNextVar(HashSet<Integer> variables, Collection<Solution> open) {
        Optional<Integer> next = nextVar(variables);
        if (next.isPresent()) {
            open.add(this.addVar(next.get()));
            open.add(this.addVar(-next.get()));
        }
    }

    public void addNextVars(HashSet<Integer> variables, Collection<Solution> open, Collection<Solution> closed) {
        for (int var :
                nextVars(variables)) {
            Solution solution = this.addVar(var);
            if (!closed.contains(solution)) open.add(solution);
            solution = this.addVar(-var);
            if (!closed.contains(solution)) open.add(solution);
        }
    }

    private Solution addVar(int var) {
        Solution newSol = new Solution(literals);
        newSol.literals.add(var);
        return newSol;
    }

    public boolean contains(int var) {
        return literals.contains(var);
    }

    public int size() {
        return literals.size();
    }

    @Override
    public String toString() {
        return "Solution{" +
                "literals=" + literals +
                '}';
    }
}