package com.meta;

public class Problem {
    private Clause[] clauses;
    public int[] variables;

    public Problem(int[] variables, Clause[] clauses) {
        this.variables = variables;
        this.clauses = clauses;
    }

    public boolean satisfy(Solution solution) {
        for (Clause c :
                clauses)
            if (!c.satisfy(solution)) return false;

        return true;
    }
}
