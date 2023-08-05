package org.example.domain;

import org.example.impl.rules.*;

public enum Option {

    ONES("Ones"),
    TWOS("Twos"),
    THREES("Threes"),
    FOURTHS("Fourths"),
    FIVES("Fives"),
    SIXES("Sixes"),
    BONUS("Bonus"),
    UPPER_SUM("Upper sum"),
    ONE_PAIR("One Pair"),
    TWO_PAIRS("Two pairs"),
    THREE_OF_A_KIND("Three of a kind"),
    FOUR_OF_A_KIND("Four of a Kind"),
    SMALL_STRAIGHT("Small Straight"),
    LARGE_STRAIGHT("Large Straight"),
    FULL_HOUSE("Full house"),
    CHANCE("Chance"),
    YATZY("Yatzy"),
    TOTAL_SUM("Sum");


    private final String name;

    Option(String name) {
        this.name = name;
    }

    public Rules getRule() {
        return switch (this) {
            case ONES -> new SingleDigitRule(1);
            case TWOS -> new SingleDigitRule(2);
            case THREES -> new SingleDigitRule(3);
            case FOURTHS -> new SingleDigitRule(4);
            case FIVES -> new SingleDigitRule(5);
            case SIXES -> new SingleDigitRule(6);
            case BONUS, UPPER_SUM, TOTAL_SUM -> new NoRule();
            case ONE_PAIR -> new EqualCardsRule(2);
            case TWO_PAIRS -> new TwoPairsRule();
            case THREE_OF_A_KIND -> new EqualCardsRule(3);
            case FOUR_OF_A_KIND -> new EqualCardsRule(4);
            case SMALL_STRAIGHT -> new SmallStraightRule();
            case LARGE_STRAIGHT -> new LargeStraightRule();
            case FULL_HOUSE -> new FullHouseRule();
            case CHANCE -> new ChanceRule();
            case YATZY -> new EqualCardsRule(5);
        };
    }

    public String getName() {
        return name;
    }

    public boolean isPartOfUpperSheet() {
        return switch (this) {
            case ONES,
                    TWOS,
                    THREES,
                    FOURTHS,
                    FIVES,
                    SIXES,
                    BONUS -> true;
            default -> false;
        };
    }

    public boolean isPartOfTotalSum() {
        return switch (this) {
            case UPPER_SUM,
                    ONE_PAIR,
                    TWO_PAIRS,
                    THREE_OF_A_KIND,
                    FOUR_OF_A_KIND,
                    SMALL_STRAIGHT,
                    LARGE_STRAIGHT,
                    FULL_HOUSE,
                    CHANCE,
                    YATZY -> true;
            default -> false;
        };
    }
}
