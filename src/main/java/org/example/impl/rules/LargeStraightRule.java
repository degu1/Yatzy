package org.example.impl.rules;

import java.util.Arrays;

public class LargeStraightRule implements Rules{
    @Override
    public boolean isCrossedOut(int[] dice) {
        Integer[] sortedBucket = RuleHelper.sortInBuckets(dice);
        Integer[] expectedBucket = {0,1,1,1,1,1};
        return !Arrays.equals(sortedBucket, expectedBucket);
    }

    @Override
    public int calculatePoints(int[] dice) {
        return 20;
    }
}
