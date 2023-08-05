package org.example.impl.rows;

import org.example.domain.Option;

import java.util.Map;

public class RowUpperSum extends Row {
    public RowUpperSum(Option option) {
        super(option);
    }

    @Override
    public String getValue() {
        if (hasPoints()) return String.valueOf(points);
        return "";
    }

    @Override
    public void update(Map<Option, Row> rows) {
        points = rows.values().stream()
                .filter(r -> r.getOption().isPartOfUpperSheet())
                .filter(Row::hasPoints)
                .mapToInt(Row::getPoints)
                .sum();

    }
}
