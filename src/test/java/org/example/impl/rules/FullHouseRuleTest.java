package org.example.impl.rules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class FullHouseRuleTest {
    private static FullHouseRule fullHouseRule;
    private final int[] SMALL_STRAIGHT = {1,2,3,4,5};
    private final int[] FULL_HOUSE = {2,2,5,5,5};

    @BeforeAll
    static void setup(){
        fullHouseRule = new FullHouseRule();
    }
    @Test
    void isCrossedOut() {
        assertThat(fullHouseRule.isCrossedOut(SMALL_STRAIGHT)).isTrue();
    }

    @Test
    void isNotCrossedOut() {
        assertThat(fullHouseRule.isCrossedOut(FULL_HOUSE)).isFalse();
    }

    @Test
    void calculatePoints() {
        int result = fullHouseRule.calculatePoints(FULL_HOUSE);
        assertThat(result).isEqualTo(19);
    }
}