package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * Grants (or denies) this role access to a hierarchy. access may be "all",
 * "custom" or "none". If access is "custom", you may also specify the
 * attributes topLevel, bottomLevel, and the member grants. See
 * mondrian.olap.Role#grant(mondrian.olap.Hierarchy, int, mondrian.olap.Level).
 */
public class HierarchyGrant extends Grant {
    private String hierarchy; // required attribute

    private String topLevel; // optional attribute

    private String bottomLevel; // optional attribute

    private List<MemberGrant> memberGrants; // optional array

    public HierarchyGrant() {
	super();
	memberGrants = new ArrayList<MemberGrant>();
    }

    public HierarchyGrant(String hierarchy) {
	this();
	this.hierarchy = hierarchy;
    }

    public String getName() {
	return "HierarchyGrant";
    }

    public String getHierarchy() {
	return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
	this.hierarchy = hierarchy;
    }

    public String getTopLevel() {
	return topLevel;
    }

    public void setTopLevel(String topLevel) {
	this.topLevel = topLevel;
    }

    public String getBottomLevel() {
	return bottomLevel;
    }

    public void setBottomLevel(String bottomLevel) {
	this.bottomLevel = bottomLevel;
    }

    public void addMemberGrants(MemberGrant memberGrant) {
	if (memberGrant != null) {
	    memberGrants.add(memberGrant);
	}
    }

}
