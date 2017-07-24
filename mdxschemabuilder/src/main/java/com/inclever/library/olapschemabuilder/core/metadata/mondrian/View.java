package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.SQLDialet;

/**
 * A collection of SQL statements, one per dialect.
 */
public class View extends Relation {
    private String alias; // required attribute

    private List<SQL> selects; // min 1

    public View() {
	selects = new ArrayList<SQL>();
    }

    public View(String alias) {
	this();
	this.alias = alias;
    }

    /**
     * Copy constructor.
     */
    protected View(View view) {
	this(view.alias);
    }

    public void setAlias(String alias) {
	this.alias = alias;
    }

    public String getAlias() {
	return alias;
    }

    public void addSelect(SQL select) {
	selects.add(select);
    }

    public List<SQL> getSelects() {
	return selects;
    }

    public String toString() {
	return selects.get(0).getCdata();
    }

    public View find(String seekAlias) {
	if (seekAlias.equals(alias)) {
	    return this;
	} else {
	    return null;
	}
    }

    /*
     * public SqlQuery.CodeSet getCodeSet() { return SQL.toCodeSet(selects); }
     */

    public void addCode(SQLDialet dialect, String code) {
	if (selects == null) {
	    selects = new ArrayList<SQL>();
	} else {
	    List<SQL> olds = selects;
	    selects = new ArrayList<SQL>(olds);

	}

	SQL sql = new SQL();
	sql.setDialect(dialect);
	sql.setCdata(code);
	selects.add(sql);
    }

}
