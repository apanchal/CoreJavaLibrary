package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * Not used
 */
public class MemberReaderParameter {
    private String name; // optional attribute
    private String value; // optional attribut

    public MemberReaderParameter() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
