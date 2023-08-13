package org.example.api;

import org.example.domain.Dice;
import org.example.domain.Exeptions.OptionException;
import org.example.domain.Option;
import org.example.domain.Player;


import java.util.List;
import java.util.Optional;

public interface PlayYatzy {
    /**
     *
     * @param players list of players.
     * @return a string of the format that the ResponseFormatter defines.
     */
    String setup(List<Player> players);

    /**
     *
     * @param option that the player wants to populate.
     * @param dice   the result of the dice that has been played.
     * @return a String of the format that the ResponseFormatter defines.
     * @throws OptionException when the option is already used by player.
     */
    String nextPlay(Player player, Option option, Dice dice) throws OptionException;

    /**
     *
     * @return Optional of Player, if empty the game is finished.
     */
    Optional<Player> nextPlayer();
}
