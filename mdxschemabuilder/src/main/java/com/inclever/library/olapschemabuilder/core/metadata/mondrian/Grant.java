package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.GrantAccessValue;

public abstract class Grant {

    private GrantAccessValue accessValue; // required attribute

    /**
     * Instantiates a new grant with default access (all).
     */
    public Grant() {
	accessValue = GrantAccessValue.ALL;
    }

    /**
     * Instantiates a new grant with given access.
     * 
     * @param access
     *            the access
     */
    public Grant(GrantAccessValue accessValue) {
	this.accessValue = accessValue;
    }

    public GrantAccessValue getAccess() {
	return accessValue;
    }

    public void setAccessValue(GrantAccessValue accessValue) {
	this.accessValue = accessValue;
    }

}
