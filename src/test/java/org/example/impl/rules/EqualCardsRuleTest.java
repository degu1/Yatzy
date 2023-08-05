package org.example.impl.rules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualCardsRuleTest {
    private static EqualCardsRule onePairRule;
    private static EqualCardsRule threeOfAKindRule;
    private static EqualCardsRule yatzyRule;
    private final int[] NO_PAIR = {2, 3, 4, 5, 6};
    private final int[] ONE_PAIR = {1, 2, 1, 1, 1};
    private final int[] TWO_PAIRS_BIGGEST_FIVES = {1, 1, 5, 6, 5};
    private final int[] NO_THREE_OF_A_KIND = {2, 3, 4, 5, 6};
    private final int[] THREE_OF_A_KIND = {4, 2, 3, 3, 3};
    private final int[] YATZY = {3, 3, 3, 3, 3};

    @BeforeAll
    static void setup() {
        onePairRule = new EqualCardsRule(2);
        threeOfAKindRule = new EqualCardsRule(3);
        yatzyRule = new EqualCardsRule(5);
    }

    @Test
    void isCrossedOut() {
        assertThat(onePairRule.isCrossedOut(NO_PAIR)).isTrue();
        assertThat(threeOfAKindRule.isCrossedOut(NO_THREE_OF_A_KIND)).isTrue();

    }

    @Test
    void isNotCrossedOut() {
        assertThat(onePairRule.isCrossedOut(ONE_PAIR)).isFalse();
        assertThat(threeOfAKindRule.isCrossedOut(THREE_OF_A_KIND)).isFalse();

    }

    @Test
    void calculatePointsOnePair() {
        int result = onePairRule.calculatePoints(ONE_PAIR);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void calculatePointsUseBiggestPair() {
        int result = onePairRule.calculatePoints(TWO_PAIRS_BIGGEST_FIVES);

        assertThat(result).isEqualTo(10);
    }

    @Test
    void calculatePointsThreeOfAKind() {
        int result = threeOfAKindRule.calculatePoints(THREE_OF_A_KIND);

        assertThat(result).isEqualTo(9);
    }

    @Test
    void calculatePointsYatzy() {
        int result = yatzyRule.calculatePoints(YATZY);

        assertThat(result).isEqualTo(50);
    }
}