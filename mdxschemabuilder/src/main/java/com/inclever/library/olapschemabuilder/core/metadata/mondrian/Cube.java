package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition of a cube.
 */
public final class Cube {
    private String name; // required attribute

    private String defaultMeasure; // optional attribute

    private String caption; // optional attribute

    private Boolean cache = Boolean.TRUE; // attribute default: true

    private Boolean enabled = Boolean.TRUE; // attribute default: true

    /**
     * The fact table is the source of all measures in this cube. If this is a
     * Table and the schema name is not present, table name is left unqualified.
     */
    private Relation fact; // required element

    /**  */
    private List<CubeDimension> dimensions; // optional array

    /**  */
    private List<Measure> measures; // optional array

    /** Calculated members in this cube. */
    private List<CalculatedMember> calculatedMembers; // optional array

    /** Named sets in this cube. */
    private List<NamedSet> namedSets; // optional array

    public Cube() {
	dimensions = new ArrayList<CubeDimension>();
	measures = new ArrayList<Measure>();
	calculatedMembers = new ArrayList<CalculatedMember>();
	namedSets = new ArrayList<NamedSet>();
    }

    public Cube(String name) {
	this();
	this.name = name;
    }

    public Cube(String cubeName, Relation fact) {
	this(cubeName);
	this.fact = fact;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void addCubeDimensions(CubeDimension cubeDimension) {
	if (cubeDimension != null) {
	    dimensions.add(cubeDimension);
	}
    }

    public void addMeasures(Measure measure) {
	if (measure != null) {
	    measures.add(measure);
	}
    }

    public void addCalculatedMembers(CalculatedMember calculatedMember) {
	if (calculatedMember != null) {
	    calculatedMembers.add(calculatedMember);
	}
    }

    public void addNamedSets(NamedSet namedSet) {
	if (namedSet != null) {
	    namedSets.add(namedSet);
	}
    }

    /*
     * public void display(PrintWriter out, int indent) {
     * out.println(getName()); displayAttribute(out, "name", cubeName, indent +
     * 1); displayAttribute(out, "caption", caption, indent + 1);
     * displayAttribute(out, "cache", cache, indent + 1); displayAttribute(out,
     * "enabled", enabled, indent + 1); displayElement(out, "fact", fact, indent
     * + 1); displayElementArray(out, "dimensions", dimensions, indent + 1);
     * displayElementArray(out, "measures", measures, indent + 1);
     * displayElementArray(out, "calculatedMembers", calculatedMembers, indent +
     * 1); displayElementArray(out, "namedSets", namedSets, indent + 1); }
     * 
     * public void displayXML(XMLOutput out, int indent) { out.beginTag("Cube",
     * new XMLAttrVector().add("name", cubeName).add("caption", caption)
     * .add("cache", cache).add("enabled", enabled)); displayXMLElement(out,
     * fact); displayXMLElementArray(out, dimensions);
     * displayXMLElementArray(out, measures); displayXMLElementArray(out,
     * calculatedMembers); displayXMLElementArray(out, namedSets);
     * out.endTag("Cube"); }
     */

    public boolean isEnabled() {
	return enabled.booleanValue();
    }

    Dimension getDimension(Schema xmlSchema, String dimensionName) {
	/*
	 * for (int i = 0; i < dimensions.length; i++) { if
	 * (dimensions[i].cubeDimensionName.equals(dimensionName)) { return
	 * dimensions[i].getDimension(xmlSchema); } }
	 */
	if (dimensionName != null) {
	    for (CubeDimension cubeDimension : dimensions) {
		if (dimensionName.equals(cubeDimension.getName())) {
		    return cubeDimension.getDimension(xmlSchema);
		}
	    }
	}
	// TODO: FIX ME With Proper Exception
	// throw Util.newInternal("Cannot find dimension '" + dimensionName +
	// "' in cube '" + name + "'");
	throw new RuntimeException("Cannot find dimension '" + dimensionName
		+ "' in cube '" + name + "'");
    }

    public boolean equals(Object o) {
	return (this == o);
    }

    public void setDefaultMeasure(String defaultMeasure) {
	this.defaultMeasure = defaultMeasure;
    }

    public String getDefaultMeasure() {
	return defaultMeasure;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public Boolean getCache() {
	return cache;
    }

    public void setCache(Boolean cache) {
	this.cache = cache;
    }

    public Boolean getEnabled() {
	return enabled;
    }

    public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
    }

    public void setFact(Relation fact) {
	this.fact = fact;
    }

    public Relation getFact() {
	return fact;
    }
}
