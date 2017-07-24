package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.ParameterTypeEnum;

/**
 * A Parameter defines a schema parameter. It can be referenced from an MDX
 * statement using the ParamRef function and, if not final, its value can be
 * overridden.
 */
public class Parameter {
    private String name; // required attribute

    private String description; // optional attribute

    private ParameterTypeEnum type; // attribute default: String

    private Boolean modifiable; // attribute default: true

    private String defaultValue; // optional attribute

    public Parameter() {
	type = ParameterTypeEnum.STRING;
	modifiable = true;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public ParameterTypeEnum getParameterType() {
	return type;
    }

    public void setParameterType(ParameterTypeEnum type) {
	this.type = type;
    }

    public Boolean getModifiable() {
	return modifiable;
    }

    public void setModifiable(Boolean modifiable) {
	this.modifiable = modifiable;
    }

    public String getDefaultValue() {
	return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
	this.defaultValue = defaultValue;
    }

}
