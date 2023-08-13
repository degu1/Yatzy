package org.example.impl.rules;

import java.util.Arrays;

public class TwoPairsRule implements Rules{
    @Override
    public boolean isCrossedOut(int[] dice) {
        Integer[] sortedBucket = RuleHelper.sortInBuckets(dice);
        long count = Arrays.stream(sortedBucket)
                .filter(i -> i >= 2)
                .count();
        return count < 2;
    }

    @Override
    public int calculatePoints(int[] dice) {
        Integer[] sortedBucket = RuleHelper.sortInBuckets(dice);
        int result = 0;
        for (int i = 0; i < sortedBucket.length; i++) {
            if(sortedBucket[i] >=2){
                result += (i+1)*2;
            }
        }
        return result;
    }
}
