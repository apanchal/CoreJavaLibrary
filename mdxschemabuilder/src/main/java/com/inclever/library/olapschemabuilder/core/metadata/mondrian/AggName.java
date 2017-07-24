package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

public final class AggName extends AggTable {
    public String name; // required attribute

    public AggName() {
	super();
    }

    public AggName(String name) {
	this();
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
