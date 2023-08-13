package org.example.impl;

import org.example.api.PlayYatzy;
import org.example.domain.Dice;
import org.example.domain.Exeptions.OptionException;
import org.example.domain.Option;
import org.example.domain.Player;
import org.example.domain.ResponseFormatter;

import java.util.List;
import java.util.Optional;

public class PlayYatzyImpl implements PlayYatzy {
    Sheet sheet;
    ResponseFormatter responseFormatter;

    public PlayYatzyImpl(ResponseFormatter responseFormatter) {
        this.responseFormatter = responseFormatter;
    }

    @Override
    public String setup(List<Player> players) {
        this.sheet = Sheet.of(players, responseFormatter);
        return sheet.getResponseFormatter().getResult();
    }

    @Override
    public String nextPlay(Player player, Option option, Dice dice) throws OptionException {
        if (sheet == null) throw new OptionException("Not setup");

        sheet.addOption(player, option, dice);
        return sheet.getResponseFormatter().getResult();
    }

    @Override
    public Optional<Player> nextPlayer() {
        return sheet.nextPlayer();
    }
}
