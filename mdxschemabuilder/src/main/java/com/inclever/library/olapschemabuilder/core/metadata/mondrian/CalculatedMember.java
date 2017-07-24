package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

public class CalculatedMember {
    private String name; // required attribute

    private String formatString; // optional attribute

    private String caption; // optional attribute

    private String formula; // optional attribute

    private String dimension; // required attribute

    private Boolean visible; // optional attribute

    /**
     * MDX expression which gives the value of this member.
     */
    private Formula formulaElement; // optional element

    private List<CalculatedMemberProperty> memberProperties; // optional array

    public CalculatedMember() {
	super();
	memberProperties = new ArrayList<CalculatedMemberProperty>();
    }

    public CalculatedMember(String name, String dimension) {
	this();
	this.name = name;
	this.dimension = dimension;
    }

    public void addMemberProperties(CalculatedMemberProperty memberProperty) {
	if (memberProperty != null) {
	    memberProperties.add(memberProperty);
	}
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getFormatString() {
	return formatString;
    }

    public void setFormatString(String formatString) {
	this.formatString = formatString;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public String getDimension() {
	return dimension;
    }

    public void setDimension(String dimension) {
	this.dimension = dimension;
    }

    public Boolean getVisible() {
	return visible;
    }

    public void setVisible(Boolean visible) {
	this.visible = visible;
    }

    public Formula getFormulaElement() {
	return formulaElement;
    }

    public void setFormulaElement(Formula formulaElement) {
	this.formulaElement = formulaElement;
    }

    public void setFormula(String formula) {
	this.formula = formula;
    }

    /**
     * Returns the formula, looking for a sub-element called "Formula" first,
     * then looking for an attribute called "formula".
     */
    public String getFormula() {
	if (formulaElement != null) {
	    return formulaElement.getCdata();
	} else {
	    return formula;
	}
    }

}
