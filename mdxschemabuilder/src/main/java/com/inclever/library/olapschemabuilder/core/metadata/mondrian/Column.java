package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import mondrian.olap.Util;

import mondrian.rolap.sql.SqlQuery;

public class Column extends Expression {
    public String table; // optional attribute

    public String name; // required attribute

    public Column() {
	super();
    }

    public Column(String name) {
	this();
	this.name = name;
    }

    /** Convenience constructor. */
    public Column(String table, String name) {
	this();
	Util.assertTrue(name != null);
	this.table = table;
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getExpression(SqlQuery query) {
	return query.getDialect().quoteIdentifier(table, name);
    }

    public String getGenericExpression() {
	if (table == null) {
	    return name;
	} else {
	    return table + "." + name;
	}
    }

    public String getTableAlias() {
	return table;
    }

    public int hashCode() {
	return name.hashCode() ^ ((table == null) ? 0 : table.hashCode());
    }

    public boolean equals(Object obj) {
	if (!(obj instanceof Column)) {
	    return false;
	}
	// FIXME:
	Column that = (Column) obj;

	return this.name.equals(that.name) /*
					    * && Util.equals(this.table,
					    * that.table)
					    */;
    }

    // END pass-through code block ---
}
