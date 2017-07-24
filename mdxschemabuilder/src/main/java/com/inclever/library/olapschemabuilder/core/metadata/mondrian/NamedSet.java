package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * <p>
 * Defines a named set which can be used in queries in the same way as a set
 * defined using a WITH SET clause.
 * </p>
 * <p>
 * A named set can be defined against a particular cube, or can be global to a
 * schema. If it is defined against a cube, it is only available to queries
 * which use that cube.
 * </p>
 * <p>
 * A named set defined against a cube is not inherited by a virtual cubes
 * defined against that cube. (But you can define a named set against a virtual
 * cube.)
 * </p>
 * <p>
 * A named set defined against a schema is available in all cubes and virtual
 * cubes in that schema. However, it is only valid if the cube contains
 * dimensions with the names required to make the formula valid.
 * </p>
 */
public class NamedSet {
    private String name; // required attribute

    private String formula; // optional attribute

    /**
     * MDX expression which gives the value of this set.
     */
    private Formula formulaElement; // optional element

    public NamedSet() {
	super();
    }

    public NamedSet(String name) {
	this();
	this.name = name;
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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

}
