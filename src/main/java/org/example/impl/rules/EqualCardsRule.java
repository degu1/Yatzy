package org.example.impl.rules;

import java.util.Arrays;
import java.util.Optional;

public class EqualCardsRule implements Rules{
    int numberOfEqualCards;

    public EqualCardsRule(int numberOfEqualCards) {
        this.numberOfEqualCards = numberOfEqualCards;
    }

    @Override
    public boolean isCrossedOut(int[] dice) {
        Integer[] bucket = RuleHelper.sortInBuckets(dice);

        Optional<Integer> pair = Arrays.stream(bucket)
                .filter(i -> i >= numberOfEqualCards)
                .findFirst();
        return pair.isEmpty();
    }

    @Override
    public int calculatePoints(int[] dice) {
        //If YATZY
        if(numberOfEqualCards == 5) return 50;

        Integer[] bucket = RuleHelper.sortInBuckets(dice);
        for (int i = bucket.length - 1; i >= 0; i--) {
            if(bucket[i] >=numberOfEqualCards) {
                return (i+1)*numberOfEqualCards;
            }
        }
        return 0;
    }
}
