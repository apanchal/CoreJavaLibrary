package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

//import mondrian.olap.Util;

/**
 * A VirtualCubeDimension is a usage of a Dimension in a VirtualCube.
 */
public class VirtualCubeDimension extends CubeDimension {
    private String cubeName; // optional attribute

    public VirtualCubeDimension() {
	super();
    }

    public String getCubeName() {
	return cubeName;
    }

    public void setCubeName(String cubeName) {
	this.cubeName = cubeName;
    }

    // implement CubeDimension
    public Dimension getDimension(Schema schema) {
	// Util.assertPrecondition(schema != null, "schema != null");

	if (cubeName == null) {
	    return schema.getPublicDimension(getName());
	} else {
	    Cube cube = schema.getCube(cubeName);

	    return cube.getDimension(schema, getName());
	}
    }

    // END pass-through code block ---
}
