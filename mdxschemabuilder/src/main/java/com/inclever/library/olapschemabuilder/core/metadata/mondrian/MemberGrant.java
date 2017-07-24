package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.GrantAccessValue;

/**
 * Grants (or denies) this role access to a member. The children of this member
 * inherit that access. You can implicitly see a member if you can see any of
 * its children. See mondrian.olap.Role#grant(mondrian.olap.Member,int).
 */
public class MemberGrant {
    private String member; // required attribute

    public GrantAccessValue accessValue; // required attribute

    public MemberGrant() {
	super();
	accessValue = GrantAccessValue.ALL;
    }

    public MemberGrant(String member, GrantAccessValue accessValue) {
	this();
	this.member = member;
	this.accessValue = accessValue;
    }

    public String getMember() {
	return member;
    }

    public void setMember(String member) {
	this.member = member;
    }

    public GrantAccessValue getAccess() {
	return accessValue;
    }

}
