package com.inclever.library.sqlquerybuilder.core;

public enum Aggerator {

    AVG("avg"), MAX("max"), MIN("min"), COUNT("count");

    private String aggName;

    Aggerator(String aggName) {
        this.aggName = aggName;
    }

    public String getAggName() {
        return aggName;
    }
}
