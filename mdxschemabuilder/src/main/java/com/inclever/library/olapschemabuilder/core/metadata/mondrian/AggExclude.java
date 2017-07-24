package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

public final class AggExclude {

    private String pattern; // optional attribute

    private String name; // optional attribute

    private Boolean ignorecase; // attribute default: true

    public AggExclude() {
	ignorecase = Boolean.TRUE;
    }

    public AggExclude(String name, String pattern, Boolean ignorecase) {
	this();
	this.name = name;
	this.pattern = pattern;
	this.ignorecase = ignorecase;

    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPattern() {
	return pattern;
    }

    public void setPattern(String pattern) {
	this.pattern = pattern;
    }

    public boolean isIgnoreCase() {
	return ignorecase.booleanValue();
    }

    public void setIgnorecase(Boolean ignorecase) {
	this.ignorecase = ignorecase;
    }

}
