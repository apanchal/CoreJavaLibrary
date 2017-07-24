package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * A UserDefinedFunction is a function which extends the MDX language. It must
 * be implemented by a Java class which implements the interface
 * mondrian.spi.UserDefinedFunction.
 */
public class UserDefinedFunction {
    public String name; // required attribute

    public String className; // required attribute

    public UserDefinedFunction() {
	super();
    }

    public UserDefinedFunction(String name, String className) {
	this.name = name;
	this.className = className;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public String getClassName() {
	return className;
    }

}
