package org.example.impl.rows;

import org.example.domain.Option;

import java.util.Map;

public abstract class Row {
    protected Option option;
    protected Integer points;
    protected boolean crossedOut;

    public Row(Option option) {
        this.option = option;
        this.crossedOut = false;
    }

    public abstract String getValue();

    public abstract void update(Map<Option, Row> rows);

    public boolean isUsed() {
        return hasPoints() || crossedOut;
    }

    public boolean hasPoints() {
        return points != null;
    }

    public void setPoints(int points) {
        if (isUsed()) throw new RuntimeException("Tried to set points when row already used");
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public boolean isCrossedOut() {
        return crossedOut;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setCrossedOut(boolean crossedOut) {
        if (isUsed()) throw new RuntimeException("Tried to cross out when row already used");
        this.crossedOut = crossedOut;
    }


}
