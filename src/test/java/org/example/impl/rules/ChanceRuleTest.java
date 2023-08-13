package org.example.impl.rules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ChanceRuleTest {

    private static ChanceRule chanceRule;
    private final int[] DICE = {1, 2, 4, 5, 6};

    @BeforeAll
    static void setup() {
        chanceRule = new ChanceRule();
    }

    @Test
    void calculatePoints() {
        int result = chanceRule.calculatePoints(DICE);
        assertThat(result).isEqualTo(18);
    }
}