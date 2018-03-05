package com.meta;

public abstract class Algorithm {
    public String name = getClass().getSimpleName();
    public abstract Solution solve(IntanceSAT intanceSAT, long timeout);
}
