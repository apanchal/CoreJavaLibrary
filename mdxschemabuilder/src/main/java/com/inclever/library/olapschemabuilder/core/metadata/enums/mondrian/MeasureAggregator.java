package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

/**
 * Aggregation function. Allowed values are "sum", "count", "min", "max", "avg",
 * and "distinct-count". ("distinct count" is allowed for backwards
 * compatibility, but is deprecated because XML enumerated attributes in a DTD
 * cannot legally contain spaces.)
 * 
 * @author apanchal
 * 
 */
public enum MeasureAggregator {

    /**
     * 
     */
    SUM("sum"),
    /**
     * 
     */
    COUNT("count"),

    /**
     * 
     */
    MIN("min"),
    /**
     * 
     */
    MAX("max"),
    /**
     * 
     */
    AVG("avg"),
    /**
     * "distinct count" is allowed for backwards compatibility, but is
     * deprecated because XML enumerated attributes in a DTD cannot legally
     * contain spaces
     */
    DISTINCTCOUNT("distinct count");

    private String aggregator;

    MeasureAggregator(String aggregator) {
	this.aggregator = aggregator;
    }

    public String getAggregator() {
	return aggregator;
    }
}
