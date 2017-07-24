package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

public final class AggLevel {
    private String column; // required attribute

    private String name; // required attribute

    public AggLevel() {
	super();
    }

    public AggLevel(String column, String name) {
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

    public String getColumnName() {
	return column;
    }

    public void setColumn(String column) {
	this.column = column;
    }

}
