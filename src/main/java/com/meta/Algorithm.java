package com.meta;

public abstract class Algorithm {
    public String name = getClass().getSimpleName();
    public abstract Solution solve(InstanceSAT instanceSAT, long timeout);
}
