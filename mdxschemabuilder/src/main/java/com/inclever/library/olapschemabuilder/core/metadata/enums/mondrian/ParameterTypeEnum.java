package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

public enum ParameterTypeEnum {
    STRING("String"), NUMERIC("Numeric"), INTEGER("Integer"), BOOLEAN("Boolean"), DATE(
	    "Date"), TIME("Time"), TIMESTAMP("Timestamp"), MEMBER("Member");

    private String parameterType;

    private ParameterTypeEnum(String parameterType) {
	this.parameterType = parameterType;
    }

    public String getParameterType() {
	return parameterType;
    }
}
