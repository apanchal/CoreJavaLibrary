package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * Fact or dimension table.
 * 
 * @author Ashish Panchal
 * 
 */
public class Table extends Relation {
    private String name; // required attribute

    /**
     * Optional qualifier for table.
     */
    private String schema; // optional attribute

    /**
     * Alias to be used with this table when it is used to form queries. If not
     * specified, defaults to the table name, but in any case, must be unique
     * within the schema. (You can use the same table in different hierarchies,
     * but it must have different aliases.)
     */
    private String alias; // optional attribute

    /**
     * The SQL WHERE clause expression to be appended to any select statement
     */
    private SQL filter; // optional element

    private List<AggExclude> aggExcludes; // optional array

    private List<AggTable> aggTables; // optional array

    public Table() {
	super();
	aggExcludes = new ArrayList<AggExclude>();
	aggTables = new ArrayList<AggTable>();
    }

    /**
     * Create Table with name
     * 
     * @param tableName
     */
    public Table(String name) {
	this();
	this.name = name;

    }

    /**
     * Create Table with name and Schema
     * 
     * @param schema
     * @param name
     * @param alias
     */
    public Table(String schema, String name, String alias) {
	this(name);
	this.schema = schema;
	this.alias = alias;
    }

    /**
     * Create Table with passed table object
     * 
     * @param table
     */
    public Table(Table table) {
	this(table.schema, table.name, table.alias);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSchema() {
	return schema;
    }

    public void setSchema(String schema) {
	this.schema = schema;
    }

    /** Returns the alias or, if it is null, the table name. */
    public String getAlias() {
	return (alias != null) ? alias : name;
    }

    public void setAlias(String alias) {
	this.alias = alias;
    }

    public String getFilter() {
	return (filter == null) ? null : filter.getCdata();
    }

    public void setFilter(SQL filter) {
	this.filter = filter;
    }

    public List<AggExclude> getAggExcludes() {
	return aggExcludes;
    }

    public List<AggTable> getAggTables() {
	return aggTables;
    }

    public void addAggExcludes(AggExclude aggExclude) {
	aggExcludes.add(aggExclude);
    }

    public void addAggTables(AggTable aggTable) {
	aggTables.add(aggTable);
    }

    public String toString() {
	return (schema == null) ? name : (schema + "." + name);
    }

    public Table find(String seekAlias) {
	return seekAlias.equals(name) ? this : (((alias != null) && seekAlias
		.equals(alias)) ? this : null);
    }

    public boolean equals(Object o) {
	return (this == o);
    }

    public int hashCode() {
	return toString().hashCode();
    }

}
