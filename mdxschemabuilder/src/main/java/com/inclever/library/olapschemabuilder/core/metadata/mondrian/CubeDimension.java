package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * A CubeDimension is either a usage of a Dimension ('shared dimension', in
 * MSOLAP parlance), or a 'private dimension'.
 */
public abstract class CubeDimension {
    private String name; // required attribute

    private String caption; // optional attribute

    private String foreignKey; // optional attribute

    public CubeDimension() {
	super();
    }

    public CubeDimension(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public String getForeignKey() {
	return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
	this.foreignKey = foreignKey;
    }

    /**
     * Looks up the base dimension of this dimension. If this is a usage of a
     * shared dimension, returns the referenced dimension; otherwise returns the
     * dimension itself.
     * 
     * <p>
     * Never returns null; if the dimension cannot be found, throws an error.
     * 
     * @param schema
     *            Schema, never null
     * @pre schema != null
     * @post return != null
     */
    public abstract Dimension getDimension(Schema schema);
}
