package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

/**
 * Enumerates the types of dimensions.
 * 
 * @author Ashish Panchal
 */
public enum DimensionType {
    /**
     * Indicates that the dimension is not related to time.
     */
    StandardDimension("StandardDimension"),
    /**
     * Indicates that a dimension is a time dimension.
     */
    TimeDimension("TimeDimension"),
    /**
     * Indicates the a dimension is the measures dimension.
     */
    MeasuresDimension("Measures");

    private String type;

    private DimensionType(String type) {
	this.type = type;
    }

    public String getDimensionType() {
	return type;
    }

}
// End DimensionType.java
