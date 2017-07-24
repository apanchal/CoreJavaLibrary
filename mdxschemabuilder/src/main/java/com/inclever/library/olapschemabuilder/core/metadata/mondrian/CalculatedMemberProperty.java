package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * Property of a calculated member defined against a cube. It must have either
 * an expression or a value.
 */
public class CalculatedMemberProperty {
    private String name; // required attribute

    private String caption; // optional attribute

    private String expression; // optional attribute

    private String value; // optional attribute

    public CalculatedMemberProperty() {
	super();
    }

    public CalculatedMemberProperty(String name) {
	this();
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public String getExpression() {
	return expression;
    }

    public void setExpression(String expression) {
	this.expression = expression;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
