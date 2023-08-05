package org.example.domain;

import org.example.impl.Sheet;

public abstract class ResponseFormatter {
    protected Sheet sheet;

    public abstract String getResult();

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}
