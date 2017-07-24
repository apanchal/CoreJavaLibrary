package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * A DimensionUsage is usage of a shared Dimension within the context of a cube.
 */
public final class DimensionUsage extends CubeDimension {
    private String source; // required attribute

    private String level; // optional attribute

    private String usagePrefix; // optional attribute

    public DimensionUsage() {
	super();
    }

    public DimensionUsage(String source) {
	this();
	this.source = source;
    }

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    public String getLevel() {
	return level;
    }

    public void setLevel(String level) {
	this.level = level;
    }

    public String getUsagePrefix() {
	return usagePrefix;
    }

    public void setUsagePrefix(String usagePrefix) {
	this.usagePrefix = usagePrefix;
    }

    // implement CubeDimension
    public Dimension getDimension(Schema schema) {
	// TODO: FIX ME With Proper Code Refactoring
	// Util.assertPrecondition(schema != null, "schema != null");
	for (Dimension dimension : schema.getDimensions()) {
	    if (dimension.getName().equals(source)) {
		return dimension;
	    }
	}
	// TODO: FIX ME With Proper Exception
	// throw Util.newInternal("Cannot find shared dimension '" + source +
	// "'");
	throw new RuntimeException("Cannot find shared dimension '" + source
		+ "'");
    }
}
