package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.PropertyTypeEnum;

/**
 * Member property.
 */
public class Property {
    private String name; // optional attribute

    private String column; // optional attribute

    private PropertyTypeEnum type = PropertyTypeEnum.STRING; // attribute
							     // default: String

    private String formatter; // optional attribute

    public Property() {
	super();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getColumn() {
	return column;
    }

    public void setColumn(String column) {
	this.column = column;
    }

    public PropertyTypeEnum getType() {
	return type;
    }

    public void setType(PropertyTypeEnum type) {
	this.type = type;
    }

    public String getFormatter() {
	return formatter;
    }

    public void setFormatter(String formatter) {
	this.formatter = formatter;
    }

}
