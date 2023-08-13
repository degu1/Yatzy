package org.example.impl.rules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SmallStraightRuleTest {

    private static SmallStraightRule smallStraightRule;
    private final int[] SMALL_STRAIGHT = {1, 2, 3, 4, 5};
    private final int[] LARGE_STRAIGHT = {2, 3, 4, 5, 6};

    @BeforeAll
    static void setup() {
        smallStraightRule = new SmallStraightRule();
    }

    @Test
    void isCrossedOut() {
        assertThat(smallStraightRule.isCrossedOut(LARGE_STRAIGHT)).isTrue();
    }

    @Test
    void isNotCrossedOut() {
        assertThat(smallStraightRule.isCrossedOut(SMALL_STRAIGHT)).isFalse();
    }
}