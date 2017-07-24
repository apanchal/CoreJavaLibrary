package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.List;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.MeasureAggregator;
import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.MeasureDataType;

public class Measure {
    /** Allowable values for {@link #datatype}. */

    // TODO:The default datatype of a measure is 'Integer' if the measure's
    // aggregator is 'Count', otherwise it is 'Numeric'.
    private MeasureDataType measureDataType = MeasureDataType.Integer;

    private MeasureAggregator aggregator; // required attribute

    private String name; // required attribute

    private String column; // optional attribute

    // private String datatype; // optional attribute

    private String formatString; // optional attribute

    // private String aggregator; // required attribute

    private String formatter; // optional attribute

    private String caption; // optional attribute

    private Boolean visible; // optional attribute

    /**
     * The SQL expression used to calculate a measure. Must be specified if a
     * source column is not specified.
     */
    public MeasureExpression measureExp; // optional element

    public List<CalculatedMemberProperty> memberProperties; // optional array

    public Measure() {
	super();
    }

    public Measure(String name) {
	this();
	this.name = name;
    }

    public Measure(String name, MeasureAggregator aggregator) {
	this(name);
	this.aggregator = aggregator;
    }

    public MeasureDataType getMeasureDataType() {
	return measureDataType;
    }

    public void setMeasureDataType(MeasureDataType measureDataType) {
	this.measureDataType = measureDataType;
    }

    public MeasureAggregator getAggregator() {
	return aggregator;
    }

    public void setAggregator(MeasureAggregator aggregator) {
	this.aggregator = aggregator;
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

    public String getFormatString() {
	return formatString;
    }

    public void setFormatString(String formatString) {
	this.formatString = formatString;
    }

    public String getFormatter() {
	return formatter;
    }

    public void setFormatter(String formatter) {
	this.formatter = formatter;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public Boolean getVisible() {
	return visible;
    }

    public void setVisible(Boolean visible) {
	this.visible = visible;
    }

    public MeasureExpression getMeasureExp() {
	return measureExp;
    }

    public void setMeasureExp(MeasureExpression measureExp) {
	this.measureExp = measureExp;
    }

    /*
     * public List<CalculatedMemberProperty> getMemberProperties() { return
     * memberProperties; }
     */

    public void addMemberProperties(
	    CalculatedMemberProperty calculatedMemberProperty) {
	memberProperties.add(calculatedMemberProperty);
    }

}
