package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * Grants (or denies) this role access to a cube. access may be "all" or "none".
 * See mondrian.olap.Role#grant(mondrian.olap.Cube,int).
 */
public class CubeGrant extends Grant {
    private String cubeName; // required attribute

    private List<DimensionGrant> dimensionGrants; // optional array

    private List<HierarchyGrant> hierarchyGrants; // optional array

    public CubeGrant() {
	super();
	dimensionGrants = new ArrayList<DimensionGrant>();
	hierarchyGrants = new ArrayList<HierarchyGrant>();
    }

    public CubeGrant(String cube) {
	this();
	this.cubeName = cube;

    }

    public String getCubeName() {
	return cubeName;
    }

    public void setCubeName(String cubeName) {
	this.cubeName = cubeName;
    }

    public void addDimensionGrants(DimensionGrant dimensionGrant) {
	if (dimensionGrant != null) {
	    dimensionGrants.add(dimensionGrant);
	}
    }

    public void addHierarchyGrants(HierarchyGrant hierarchyGrant) {
	if (hierarchyGrant != null) {
	    hierarchyGrants.add(hierarchyGrant);
	}
    }

}
