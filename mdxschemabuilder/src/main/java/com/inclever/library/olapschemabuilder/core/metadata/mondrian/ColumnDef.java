package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * Column definition for an inline table.
 */
public final class ColumnDef {
    public String name; // required attribute

    public String type; // required attribute

    public ColumnDef() {
	super();
    }

    public ColumnDef(String name) {
	this();
	this.name = name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

}
