package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * A VirtualCube is a set of dimensions and measures gleaned from other cubes.
 */
public class VirtualCube {
    private Boolean enabled = Boolean.TRUE; // attribute default: true

    private String name; // optional attribute

    private String caption; // optional attribute

    /**
     */
    private List<VirtualCubeDimension> dimensions; // optional array

    /**
    */
    private List<VirtualCubeMeasure> measures; // optional array

    /**
     * Calculated members that belong to this virtual cube. (Calculated members
     * inherited from other cubes should not be in this list.)
     */
    private List<CalculatedMember> calculatedMembers; // optional array

    public VirtualCube() {
	super();
	dimensions = new ArrayList<VirtualCubeDimension>();
	measures = new ArrayList<VirtualCubeMeasure>();
	calculatedMembers = new ArrayList<CalculatedMember>();
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public boolean isEnabled() {
	return enabled.booleanValue();
    }

    public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public void addDimensions(VirtualCubeDimension virtualCubeDimension) {
	if (virtualCubeDimension != null) {
	    dimensions.add(virtualCubeDimension);
	}
    }

    public void addMeasures(VirtualCubeMeasure virtualCubeMeasure) {
	if (virtualCubeMeasure != null) {
	    measures.add(virtualCubeMeasure);
	}
    }

    public void addCalculatedMembers(CalculatedMember calculatedMember) {
	if (calculatedMember != null) {
	    calculatedMembers.add(calculatedMember);
	}
    }
}
