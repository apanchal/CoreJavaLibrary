package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * The name of the column mapping from base fact table foreign key to aggregate
 * table foreign key.
 */
public final class AggForeignKey {
    private String factColumn; // required attribute

    private String aggColumn; // required attribute

    public AggForeignKey() {
	super();
    }

    public AggForeignKey(String factColumn, String aggColumn) {
	this.factColumn = factColumn;
	this.aggColumn = aggColumn;
    }

    public String getFactColumn() {
	return factColumn;
    }

    public void setFactColumn(String factColumn) {
	this.factColumn = factColumn;
    }

    public String getAggColumn() {
	return aggColumn;
    }

    public void setAggColumn(String aggColumn) {
	this.aggColumn = aggColumn;
    }

}
