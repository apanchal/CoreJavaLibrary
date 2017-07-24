package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * A definition of an aggregate table for a base fact table. This aggregate
 * table must be in the same schema as the base fact table.
 */
public abstract class AggTable {
    private Boolean ignorecase; // attribute default: true

    /**
     * What does the fact_count column look like.
     */
    private AggFactCount factCount; // required element

    private List<AggIgnoreColumn> ignoreColumns; // optional array

    private List<AggForeignKey> foreignKeys; // optional array

    private List<AggMeasure> measures; // optional array

    private List<AggLevel> levels; // optional array

    protected AggTable() {
	super();
	ignorecase = true;
	ignoreColumns = new ArrayList<AggIgnoreColumn>();
	foreignKeys = new ArrayList<AggForeignKey>();
	measures = new ArrayList<AggMeasure>();
	levels = new ArrayList<AggLevel>();
    }

    protected AggTable(AggFactCount factCount) {
	this();
	this.factCount = factCount;
    }

    public boolean isIgnoreCase() {
	return ignorecase.booleanValue();
    }

    public AggFactCount getFactCount() {
	return factCount;
    }

    public void setFactCount(AggFactCount factCount) {
	this.factCount = factCount;
    }

    public List<AggIgnoreColumn> getAggIgnoreColumns() {
	return ignoreColumns;
    }

    public void addAggIgnoreColumn(AggIgnoreColumn column) {
	ignoreColumns.add(column);
    }

    public List<AggForeignKey> getAggForeignKeys() {
	return foreignKeys;
    }

    public void addForeignKey(AggForeignKey aggForeignKey) {
	foreignKeys.add(aggForeignKey);
    }

    public List<AggMeasure> getAggMeasures() {
	return measures;
    }

    public void addAggMeasure(AggMeasure aggMeasure) {
	measures.add(aggMeasure);
    }

    public List<AggLevel> getAggLevels() {
	return levels;
    }

    public void addAggLevel(AggLevel aggLevel) {
	levels.add(aggLevel);
    }

}
