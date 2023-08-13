package org.example.impl.rows;

import org.example.domain.Option;

import java.util.Map;

public class RowPlayable extends Row {
    public RowPlayable(Option option) {
        super(option);
    }

    @Override
    public String getValue() {
        if (crossedOut) return "X";
        if (hasPoints()) return String.valueOf(points);
        return "";
    }

    @Override
    public void update(Map<Option, Row> rows) {
        throw new RuntimeException("Method should not be called");
    }
}
