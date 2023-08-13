package org.example.impl.rules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingleDigitRuleTest {
    private static SingleDigitRule onesRule;
    private static SingleDigitRule twosRule;
    private final int[] NO_ONES_AND_ONE_TWO = {2, 3, 4, 5, 6};
    private final int[] ONES_SUM_4 = {1, 2, 1, 1, 1};

    @BeforeAll
    static void setup() {
        onesRule = new SingleDigitRule(1);
        twosRule = new SingleDigitRule(2);
    }

    @Test
    void isCrossedOutTest() {
        boolean crossedOut = onesRule.isCrossedOut(NO_ONES_AND_ONE_TWO);
        assertThat(crossedOut).isTrue();
    }

    @Test
    void isNotCrossedOutTest() {
        boolean crossedOut = onesRule.isCrossedOut(ONES_SUM_4);
        assertThat(crossedOut).isFalse();
    }

    @Test
    void calculatePointsOneTest() {
        int actualSum = onesRule.calculatePoints(ONES_SUM_4);
        assertThat(actualSum).isEqualTo(4);
    }

    @Test
    void calculatePointsTwoTest() {
        int actualSum = twosRule.calculatePoints(NO_ONES_AND_ONE_TWO);
        assertThat(actualSum).isEqualTo(2);
    }
}