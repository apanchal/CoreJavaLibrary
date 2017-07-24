package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

public final class AggPattern extends AggTable {
    private String pattern; // required attribute

    private List<AggExclude> excludes; // optional array

    public AggPattern() {
	super();
	excludes = new ArrayList<AggExclude>();
    }

    public AggPattern(String pattern) {
	this();
	this.pattern = pattern;
    }

    public String getPattern() {
	return pattern;
    }

    public void setPattern(String pattern) {
	this.pattern = pattern;
    }

    public List<AggExclude> getAggExcludes() {
	return excludes;
    }

    public void addExclude(AggExclude aggExclude) {
	excludes.add(aggExclude);

    }

}
