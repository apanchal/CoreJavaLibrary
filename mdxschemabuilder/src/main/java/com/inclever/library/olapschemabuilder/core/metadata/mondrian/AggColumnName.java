package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

public abstract class AggColumnName {
    private String column; // required attribute

    protected AggColumnName() {
	super();
    }

    protected AggColumnName(String column) {
	this();
	this.column = column;
    }

    public String getColumn() {
	return column;
    }

    public void setColumn(String column) {
	this.column = column;
    }

}
