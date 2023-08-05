package org.example.impl.rules;

public class NoRule implements Rules{
    @Override
    public boolean isCrossedOut(int[] dice) {
        return false;
    }

    @Override
    public int calculatePoints(int[] dice) {
        return 0;
    }
}
