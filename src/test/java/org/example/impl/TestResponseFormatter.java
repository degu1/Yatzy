package org.example.impl;

import org.example.domain.Option;
import org.example.domain.ResponseFormatter;
import org.example.impl.rows.Row;

import java.util.List;
import java.util.Map;

public class TestResponseFormatter extends ResponseFormatter {
    @Override
    public String getResult() {
        StringBuilder sb = new StringBuilder();

        for (Sheet.PlayerColum playerColum : sheet.playerColumns) {
            sb.append("Name: ");
            sb.append(playerColum.getPlayer().name());
            sb.append(", ");
            for (Row row : getSortedRows(playerColum.getRows())) {
                sb.append(row.getOption().getName());
                sb.append(": ");
                if (row.isUsed()) {
                    if (row.isCrossedOut()) {
                        sb.append("X");
                    } else {
                        sb.append(row.getValue());
                    }
                } else {
                    sb.append(" ");
                }
                sb.append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<Row> getSortedRows(Map<Option, Row> rows) {
        return List.of(rows.get(Option.ONES),
                rows.get(Option.TWOS),
                rows.get(Option.THREES),
                rows.get(Option.FOURTHS),
                rows.get(Option.FIVES),
                rows.get(Option.SIXES),
                rows.get(Option.BONUS),
                rows.get(Option.UPPER_SUM),
                rows.get(Option.ONE_PAIR),
                rows.get(Option.TWO_PAIRS),
                rows.get(Option.THREE_OF_A_KIND),
                rows.get(Option.FOUR_OF_A_KIND),
                rows.get(Option.SMALL_STRAIGHT),
                rows.get(Option.LARGE_STRAIGHT),
                rows.get(Option.FULL_HOUSE),
                rows.get(Option.CHANCE),
                rows.get(Option.YATZY),
                rows.get(Option.TOTAL_SUM));
    }
}
