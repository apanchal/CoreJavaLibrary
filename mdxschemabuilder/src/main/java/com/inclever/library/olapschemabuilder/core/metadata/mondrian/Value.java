package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * Column value for an inline table. The CDATA holds the value of the column.
 */
public class Value {
    private String column; // required attribute

    private String cdata; // All text goes here

    public Value() {
	super();
    }

    public Value(String column) {
	this.column = column;
    }

    public void setColumn(String column) {
	this.column = column;
    }

    public String getColumn() {
	return column;
    }

    public void setCdata(String cdata) {
	this.cdata = cdata;
    }

    public String getCdata() {
	return cdata;
    }

}
