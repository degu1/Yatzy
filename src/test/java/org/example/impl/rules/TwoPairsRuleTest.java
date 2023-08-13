package org.example.impl.rules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoPairsRuleTest {

    private static TwoPairsRule twoPairsRule;
    private final int[] ONE_PAIR = {1, 2, 1, 1, 1};
    private final int[] TWO_PAIRS = {1, 1, 5, 6, 5};

    @BeforeAll
    static void setup() {
        twoPairsRule = new TwoPairsRule();
    }

    @Test
    void isCrossedOut() {
        assertThat(twoPairsRule.isCrossedOut(ONE_PAIR)).isTrue();
    }

    @Test
    void isNotCrossedOut() {
        assertThat(twoPairsRule.isCrossedOut(TWO_PAIRS)).isFalse();
    }


    @Test
    void calculatePoints() {
        int result = twoPairsRule.calculatePoints(TWO_PAIRS);
        assertThat(result).isEqualTo(12);
    }
}