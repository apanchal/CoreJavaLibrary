package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * Grants (or denies) this role access to a dimension. access may be "all" or
 * "none". Note that a role is implicitly given access to a dimension when it is
 * given acess to a cube. See also the "all_dimensions" option of the
 * "SchemaGrant" element. See
 * mondrian.olap.Role#grant(mondrian.olap.Dimension,int).
 */
public final class DimensionGrant extends Grant {
    private String dimension; // required attribute

    protected DimensionGrant() {
	super();
    }

    protected DimensionGrant(String dimension) {
	this();
	this.dimension = dimension;
    }

    public String getDimension() {
	return dimension;
    }

    public void setDimension(String dimension) {
	this.dimension = dimension;
    }

}
