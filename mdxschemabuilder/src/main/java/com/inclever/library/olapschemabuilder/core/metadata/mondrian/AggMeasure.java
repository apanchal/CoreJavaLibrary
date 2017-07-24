package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

public final class AggMeasure {
    private String column; // required attribute

    private String name; // required attribute

    public AggMeasure() {
	super();
    }

    public AggMeasure(String column, String name) {
	this();
	this.column = column;
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getColumn() {
	return column;
    }

    public void setColumn(String column) {
	this.column = column;
    }

}
