package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

public enum PropertyTypeEnum {
    STRING("String"), NUMERIC("Numeric"), INTEGER("Integer"), BOOLEAN("Boolean"), DATE(
	    "Date"), TIME("Time"), TIMESTAMP("Timestamp");

    private String type;

    private PropertyTypeEnum(String type) {
	this.type = type;
    }

    public String getType() {
	return type;
    }
}
