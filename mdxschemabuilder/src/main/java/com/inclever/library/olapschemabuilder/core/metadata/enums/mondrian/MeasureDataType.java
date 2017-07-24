package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

/**
 * The datatype of this measure: String, Numeric, Integer, Boolean, Date, Time
 * or Timestamp.
 * 
 * The default datatype of a measure is 'Integer' if the measure's aggregator is
 * 'Count', otherwise it is 'Numeric'.
 * 
 * @author apanchal
 * 
 */
public enum MeasureDataType {

    String("String"), Numeric("Numeric"), Integer("Integer"), Boolean("Boolean"), Date(
	    "Date"), Time("Time"), Timestamp("Timestamp");

    private String dataType;

    MeasureDataType(String dataType) {
	this.dataType = dataType;
    }

    public String getMeasureDataType() {
	return dataType;
    }
}
