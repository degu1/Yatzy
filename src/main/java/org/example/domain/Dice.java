package org.example.domain;

import org.example.domain.Exeptions.DiceException;

import java.util.Arrays;

public class Dice {
    private final int[] dice;

    /**
     *
     * @param dice Array of length 5 and values in range 1-6.
     */
    private Dice(int[] dice) {
        this.dice = dice;
    }

    /**
     * @throws DiceException if there are not 5 dice or if any dice is not between 1 and 6.
     */
    public static Dice of(int[] dice) throws DiceException {
        checkDice(dice);
        return new Dice(dice);
    }

    private static void checkDice(int[] dice) throws DiceException {
        if (dice.length != 5) throw new DiceException();

        long NumberOfDiceBetweenOneAndSix = Arrays.stream(dice)
                .filter(i -> 1 <= i && i <= 6)
                .count();
        if (NumberOfDiceBetweenOneAndSix != 5) throw new DiceException();
    }

    public int[] getDice() {
        return dice;
    }
}
