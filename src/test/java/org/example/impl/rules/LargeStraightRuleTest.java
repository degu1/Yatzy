package org.example.impl.rules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LargeStraightRuleTest {

    private static LargeStraightRule largeStraightRule;
    private final int[] SMALL_STRAIGHT = {1, 2, 3, 4, 5};
    private final int[] LARGE_STRAIGHT = {2, 3, 4, 5, 6};

    @BeforeAll
    static void setup() {
        largeStraightRule = new LargeStraightRule();
    }
    @Test
    void isCrossedOut() {
        assertThat(largeStraightRule.isCrossedOut(SMALL_STRAIGHT)).isTrue();
    }

    @Test
    void isNotCrossedOut() {
        assertThat(largeStraightRule.isCrossedOut(LARGE_STRAIGHT)).isFalse();
    }
}