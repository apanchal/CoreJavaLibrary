package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * Specifies the transitive closure of a parent-child hierarchy. Optional, but
 * recommended for better performance. The closure is provided as a set of
 * (parent/child) pairs: since it is the transitive closure these are actually
 * (ancestor/descendant) pairs.
 */
public class Closure {
    private String parentColumn; // required attribute

    private String childColumn; // required attribute

    private Table table; // required element

    public Closure() {
	super();
    }

    public Closure(String parentColumn, String childColumn, Table table) {
	this();
	this.parentColumn = parentColumn;
	this.childColumn = childColumn;
	this.table = table;
    }

    public String getParentColumn() {
	return parentColumn;
    }

    public void setParentColumn(String parentColumn) {
	this.parentColumn = parentColumn;
    }

    public String getChildColumn() {
	return childColumn;
    }

    public void setChildColumn(String childColumn) {
	this.childColumn = childColumn;
    }

    public Table getTable() {
	return table;
    }

    public void setTable(Table table) {
	this.table = table;
    }

}
