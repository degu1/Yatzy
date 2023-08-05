package org.example.impl;

import org.example.domain.Dice;
import org.example.domain.Exeptions.DiceException;
import org.example.domain.Exeptions.OptionException;
import org.example.domain.Option;
import org.example.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PlayYatzyImplTest {
    PlayYatzyImpl playYatzy;
    Player player1 = new Player("Dennis");
    Player player2 = new Player("Anna");

    @BeforeEach
    void setup() {
        playYatzy = new PlayYatzyImpl(new TestResponseFormatter());
    }

    @Test
    void noNamesTest() {
        assertThatThrownBy(() -> playYatzy.setup(List.of())).hasMessage("List of players is empty");
    }

    @Test
    void notPlaySameFieldTwice() throws OptionException, DiceException {
        playYatzy.setup(List.of(player1));
        Dice dice1 = Dice.of(new int[]{1, 1, 2, 1, 3});
        Dice dice2 = Dice.of(new int[]{2, 1, 2, 1, 3});
        playYatzy.nextPlay(player1, Option.ONES, dice1);
        assertThatExceptionOfType(OptionException.class).isThrownBy(() -> playYatzy.nextPlay(player1, Option.ONES, dice2));
    }

    @Test
    void happyPathTest() throws OptionException, DiceException {
        playYatzy.setup(List.of(player1, player2));

        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.ONES, Dice.of(new int[]{1, 1, 2, 6, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.ONES, Dice.of(new int[]{1, 1, 2, 6, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.TWOS, Dice.of(new int[]{2, 2, 2, 2, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.TWOS, Dice.of(new int[]{2, 2, 2, 2, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.THREES, Dice.of(new int[]{3, 4, 3, 1, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.THREES, Dice.of(new int[]{3, 4, 3, 1, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FOURTHS, Dice.of(new int[]{2, 4, 4, 5, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FOURTHS, Dice.of(new int[]{2, 4, 4, 5, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FIVES, Dice.of(new int[]{2, 5, 5, 5, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FIVES, Dice.of(new int[]{2, 5, 5, 5, 3}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.SIXES, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.SIXES, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.ONE_PAIR, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.ONE_PAIR, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.TWO_PAIRS, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.TWO_PAIRS, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.THREE_OF_A_KIND, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.THREE_OF_A_KIND, Dice.of(new int[]{6, 5, 6, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FOUR_OF_A_KIND, Dice.of(new int[]{5, 5, 5, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FOUR_OF_A_KIND, Dice.of(new int[]{5, 5, 5, 5, 6}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.SMALL_STRAIGHT, Dice.of(new int[]{1, 2, 3, 5, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.SMALL_STRAIGHT, Dice.of(new int[]{5, 2, 1, 3, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.LARGE_STRAIGHT, Dice.of(new int[]{5, 2, 6, 3, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.LARGE_STRAIGHT, Dice.of(new int[]{5, 2, 6, 3, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FULL_HOUSE, Dice.of(new int[]{2, 2, 2, 5, 5}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.FULL_HOUSE, Dice.of(new int[]{2, 2, 2, 5, 5}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.CHANCE, Dice.of(new int[]{5, 2, 6, 3, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.CHANCE, Dice.of(new int[]{5, 2, 6, 3, 4}));
        playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.YATZY, Dice.of(new int[]{2, 2, 2, 2, 2}));
        String finalResponse = playYatzy.nextPlay(playYatzy.nextPlayer().get(), Option.YATZY, Dice.of(new int[]{5, 2, 6, 3, 4}));

        String expected = """
                Name: Dennis, Ones: 2, Twos: 8, Threes: 9, Fourths: 12, Fives: 15, Sixes: 18, Bonus: 50, Upper sum: 114, One Pair: 12, Two pairs: 22, Three of a kind: 18, Four of a Kind: 20, Small Straight: 15, Large Straight: 20, Full house: 16, Chance: 20, Yatzy: 50, Sum: 307,\s
                Name: Anna, Ones: 2, Twos: 8, Threes: 9, Fourths: 12, Fives: 15, Sixes: 18, Bonus: 50, Upper sum: 114, One Pair: 12, Two pairs: 22, Three of a kind: 18, Four of a Kind: 20, Small Straight: 15, Large Straight: 20, Full house: 16, Chance: 20, Yatzy: X, Sum: 257,\s""";

        assertThat(finalResponse.trim()).isEqualTo(expected.trim());

        assertThat(playYatzy.nextPlayer().isEmpty()).isTrue();
    }
}