package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * A VirtualCubeMeasure is a usage of a Measure in a VirtualCube.
 */
public class VirtualCubeMeasure {
    private String cubeName; // optional attribute

    private String name; // optional attribute

    private Boolean visible; // optional attribute

    public VirtualCubeMeasure() {
	super();
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public String getCubeName() {
	return cubeName;
    }

    public void setCubeName(String cubeName) {
	this.cubeName = cubeName;
    }

    public Boolean getVisible() {
	return visible;
    }

    public void setVisible(Boolean visible) {
	this.visible = visible;
    }

}
