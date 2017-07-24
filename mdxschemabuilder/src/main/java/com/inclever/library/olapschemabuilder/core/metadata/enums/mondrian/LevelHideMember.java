package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

public enum LevelHideMember {
    NEVER("Never"), IF_BLANK_NAME("IfBlankName"), IF_PARENTS_NAME(
	    "IfParentsName");

    String _hideMemberIf;

    private LevelHideMember(String _hideMemberIf) {
	this._hideMemberIf = _hideMemberIf;
    }

    public String get_hideMemberIf() {
	return _hideMemberIf;
    }
}
