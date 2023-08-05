package org.example.impl.rules;

import java.util.Arrays;

public class ChanceRule implements Rules{
    @Override
    public boolean isCrossedOut(int[] dice) {
        return false;
    }

    @Override
    public int calculatePoints(int[] dice) {
        return Arrays.stream(dice).sum();
    }
}
