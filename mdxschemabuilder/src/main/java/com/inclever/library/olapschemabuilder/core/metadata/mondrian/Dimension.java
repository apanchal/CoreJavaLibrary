package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

import mondrian.olap.Util;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.DimensionType;

/**
 * A Dimension is a collection of hierarchies. There are two kinds: a public
 * dimension belongs to a Schema, and be used by several cubes; a private
 * dimension belongs to a Cube. The foreignKey field is only applicable to
 * private dimensions.
 */
public final class Dimension extends CubeDimension {

    /** Type Of the Dimension, Default is Standard */
    private DimensionType type = DimensionType.StandardDimension; // optional
								  // attribute

    /**  */
    public List<Hierarchy> hierarchies; // optional List

    /**  */
    private String usagePrefix; // optional attribute

    public Dimension() {
	super();
	hierarchies = new ArrayList<Hierarchy>();
    }

    /**
     * Instantiates a new dimension.
     * 
     * @param dimName
     *            the dim name
     */
    public Dimension(String dimName) {
	super(dimName);
	hierarchies = new ArrayList<Hierarchy>();
    }

    // implement CubeDimension
    public Dimension getDimension(Schema schema) {
	// FIXME: Need XOM API
	// Util.assertPrecondition(schema != null, "schema != null");
	Util.assertPrecondition(schema != null);
	return this;
    }

    // Return the dimension's enumerated type.
    public DimensionType getDimensionType() {
	return type;

    }

    public void setDimensionType(DimensionType type) {
	this.type = type;
    }

    public String getUsagePrefix() {
	return usagePrefix;
    }

    public void setUsagePrefix(String usagePrefix) {
	this.usagePrefix = usagePrefix;
    }

    /**
     * Adds Hierarchy in to the list of hierarchies.
     * 
     * @param hierarchy
     */
    public void addHierarchy(Hierarchy hierarchy) {
	if (hierarchy != null) {
	    hierarchies.add(hierarchy);
	}
    }

    /*
     * public void setHierarchies(List<Hierarchy> hierarchies) { if(hierarchies
     * != null) { this.hierarchies.addAll(hierarchies); }
     * 
     * }
     */

}