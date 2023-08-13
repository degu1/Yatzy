package org.example.impl.rules;

import java.util.Arrays;

public class SingleDigitRule implements Rules {
    private final int digit;

    public SingleDigitRule(int digit) {
        this.digit = digit;
    }

    @Override
    public boolean isCrossedOut(int[] dice) {
        boolean anyMatch = Arrays.stream(dice).anyMatch(i -> i == digit);
        return !anyMatch;
    }

    @Override
    public int calculatePoints(int[] dice) {
        return Arrays.stream(dice)
                .filter(i -> i == digit)
                .sum();
                   }
}
