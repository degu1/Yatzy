package org.example.impl.rules;

import java.util.Arrays;

public class RuleHelper {
    public static Integer[] sortInBuckets(int[] dice){
        Integer[] count = new Integer[6];
        Arrays.fill(count, 0);
        for (int i:dice) {
            count[i-1] += 1;
        }
        return count;
    }
}
