package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

public enum GrantAccessValue {

    ALL("all"), CUSTOM("custom"), NONE("none"), ALL_DIMENSIONS("all_dimensions");

    private String access;

    private GrantAccessValue(String access) {
	this.access = access;
    }

    public String getAccess() {
	return access;
    }

}