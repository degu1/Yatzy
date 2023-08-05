package org.example.impl.rows;

import org.example.domain.Option;

import java.util.Map;

public class RowBonus extends Row {
    public RowBonus(Option option) {
        super(option);
    }

    @Override
    public String getValue() {
        if (points != 0) return String.valueOf(points);
        return "";
    }

    @Override
    public void update(Map<Option, Row> rows) {
        int sum = rows.values().stream()
                .filter(r -> r.getOption().isPartOfUpperSheet())
                .filter(r -> r.points != null)
                .mapToInt(Row::getPoints)
                .sum();
        if (sum >= 63) {
            points = 50;
        }

    }


}
