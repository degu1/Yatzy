package org.example.impl.rules;

import java.util.Arrays;

public class SmallStraightRule implements Rules{
    @Override
    public boolean isCrossedOut(int[] dice) {
        Integer[] sortedBucket = RuleHelper.sortInBuckets(dice);
        Integer[] expectedBucket = {1,1,1,1,1,0};
        return !Arrays.equals(sortedBucket, expectedBucket);
    }

    @Override
    public int calculatePoints(int[] dice) {
        return 15;
    }
}
