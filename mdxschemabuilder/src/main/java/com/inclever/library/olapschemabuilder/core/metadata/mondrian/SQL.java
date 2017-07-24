package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.List;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.SQLDialet;

import mondrian.rolap.sql.SqlQuery;

public class SQL {
    private SQLDialet dialect; // attribute default: generic

    private String cdata; // All text goes here

    private String dialetString;

    public SQL() {
	dialect = SQLDialet.GENERIC;
	dialetString = dialect.getDialect();
    }

    public SQL(SQLDialet dialect) {
	this.dialect = dialect;
	dialetString = dialect.getDialect();
    }

    public void setDialetString(String dialetString) {
	this.dialetString = dialetString;
    }

    public String getDialetString() {
	return dialetString;
    }

    public void setCdata(String cdata) {
	this.cdata = cdata;
    }

    public String getCdata() {
	return cdata;
    }

    public void setDialect(SQLDialet dialect) {
	this.dialect = dialect;
    }

    public SQLDialet getDialect() {
	return dialect;
    }

    // BEGIN pass-through code block ---
    public int hashCode() {
	return dialect.hashCode();
    }

    public boolean equals(Object obj) {
	// Disabled this equality implementation because two sql objects
	// may be equal if their contents are equal.
	Throwable stack = new Throwable();
	stack.fillInStackTrace();

	if (!(obj instanceof SQL)) {
	    return false;
	}

	SQL that = (SQL) obj;

	// FIXME:
	return this.dialect.equals(that.dialect)
	/* && Util.equals(this.cdata, that.cdata) */;
    }

    /**
     * Converts an array of SQL to a {@link mondrian.rolap.sql.SqlQuery.CodeSet}
     * object.
     */
    public static SqlQuery.CodeSet toCodeSet(List<SQL> sqls) {
	SqlQuery.CodeSet codeSet = new SqlQuery.CodeSet();

	for (SQL sql : sqls) {
	    codeSet.put(sql.dialect.getDialect(), sql.cdata);
	}

	return codeSet;
    }

}
