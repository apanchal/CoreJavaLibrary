package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

public enum LevelDataType {
    STRING("String"), NUMERIC("Numeric"), INTEGER("Integer"), BOOLEAN("Boolean"), DATE(
	    "Date"), TIME("Time"), TIMESTAMP("Timestamp");

    String type;

    private LevelDataType(String type) {
	this.type = type;
    }

    public String getType() {
	return type;
    }
}
