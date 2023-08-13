package org.example.impl;

import org.example.domain.Dice;
import org.example.domain.Exeptions.OptionException;
import org.example.domain.Option;
import org.example.domain.Player;
import org.example.domain.ResponseFormatter;
import org.example.impl.rows.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.example.domain.Option.*;

public class Sheet {
    public List<PlayerColum> playerColumns;
    ResponseFormatter responseFormatter;


    private Sheet(List<Player> players, ResponseFormatter responseFormatter) {
        if (players.isEmpty()) throw new RuntimeException("List of players is empty");

        this.responseFormatter = responseFormatter;
        responseFormatter.setSheet(this);

        this.playerColumns = players.stream()
                .map(PlayerColum::new)
                .toList();
    }

    public static Sheet of(List<Player> player, ResponseFormatter responseFormatter) {
        return new Sheet(player, responseFormatter);
    }

    public void addOption(Player player, Option option, Dice dice) throws OptionException {
        PlayerColum playerColum = playerColumns.stream()
                .filter(pc -> pc.player.equals(player))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Something is wrong"));

        Map<Option, Row> rows = playerColum.rows;

        Row row = rows.get(option);

        if (row.isUsed()) throw new OptionException("Bad option");

        if (option.getRule().isCrossedOut(dice.getDice())) {
            row.setCrossedOut(true);
        } else {
            int i = option.getRule().calculatePoints(dice.getDice());
            row.setPoints(i);
        }

        //Calculate bonus
        rows.get(BONUS).update(playerColum.getRows());

        //Calculate upper sum
        rows.get(UPPER_SUM).update(playerColum.getRows());

        //Calculate total sum
        rows.get(TOTAL_SUM).update(playerColum.getRows());
    }

    public Optional<Player> nextPlayer() {
        if (playerColumns.size() == 0) throw new RuntimeException("No players");
        Long lastPlayersNumberLeft = playerColumns.get(playerColumns.size() - 1).numberOfTurnsLeft();
        PlayerColum playerColum = playerColumns.stream()
                .filter(p -> Objects.equals(p.numberOfTurnsLeft(), lastPlayersNumberLeft))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Something happened that is not suppose to happened!!"));
        if (playerColum.numberOfTurnsLeft() == 0) return Optional.empty();
        return Optional.of(playerColum.player);
    }

    public ResponseFormatter getResponseFormatter() {
        return responseFormatter;
    }

    public static class PlayerColum {
        private final Player player;
        private final Map<Option, Row> rows;

        public PlayerColum(Player player) {
            this.player = player;
            this.rows = Map.ofEntries(Map.entry(ONES, new RowPlayable(ONES)),
                    Map.entry(TWOS, new RowPlayable(TWOS)),
                    Map.entry(THREES, new RowPlayable(THREES)),
                    Map.entry(FOURTHS, new RowPlayable(FOURTHS)),
                    Map.entry(FIVES, new RowPlayable(FIVES)),
                    Map.entry(SIXES, new RowPlayable(SIXES)),
                    Map.entry(BONUS, new RowBonus(BONUS)),
                    Map.entry(UPPER_SUM, new RowUpperSum(UPPER_SUM)),
                    Map.entry(ONE_PAIR, new RowPlayable(ONE_PAIR)),
                    Map.entry(TWO_PAIRS, new RowPlayable(TWO_PAIRS)),
                    Map.entry(THREE_OF_A_KIND, new RowPlayable(THREE_OF_A_KIND)),
                    Map.entry(FOUR_OF_A_KIND, new RowPlayable(FOUR_OF_A_KIND)),
                    Map.entry(SMALL_STRAIGHT, new RowPlayable(SMALL_STRAIGHT)),
                    Map.entry(LARGE_STRAIGHT, new RowPlayable(LARGE_STRAIGHT)),
                    Map.entry(FULL_HOUSE, new RowPlayable(FULL_HOUSE)),
                    Map.entry(CHANCE, new RowPlayable(CHANCE)),
                    Map.entry(YATZY, new RowPlayable(YATZY)),
                    Map.entry(TOTAL_SUM, new RowTotalSum(TOTAL_SUM)));
        }

        public Player getPlayer() {
            return player;
        }

        public Map<Option, Row> getRows() {
            return rows;
        }

        public Long numberOfTurnsLeft() {
            return rows.values().stream()
                    .filter(r -> !r.isUsed())
                    .count();
        }
    }
}
