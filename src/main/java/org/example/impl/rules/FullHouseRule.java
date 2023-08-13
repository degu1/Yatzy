package org.example.impl.rules;

import java.util.Arrays;

public class FullHouseRule implements Rules{
    @Override
    public boolean isCrossedOut(int[] dice) {
        Integer[] sortedBucket = RuleHelper.sortInBuckets(dice);
        boolean pair = false;
        boolean threeOfAKind = false;
        for (int i:sortedBucket) {
            if(i == 2){
                pair = true;
            }
            if(i == 3){
                threeOfAKind = true;
            }
        }
        return !(pair && threeOfAKind);
    }

    @Override
    public int calculatePoints(int[] dice) {
        return Arrays.stream(dice).sum();
    }
}
