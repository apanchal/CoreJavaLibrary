package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

import mondrian.rolap.sql.SqlQuery;

/**
 * A collection of SQL expressions, one per dialect.
 */
public abstract class ExpressionView extends Expression {
    private List<SQL> expressions; // min 1

    public ExpressionView() {
	expressions = new ArrayList<SQL>();
    }

    public List<SQL> getExpressions() {
	return expressions;
    }

    public void addExpression(SQL sql) {
	expressions.add(sql);

    }

    // BEGIN pass-through code block ---
    public String toString() {
	return expressions.get(0).getCdata();
    }

    public String getExpression(SqlQuery query) {
	return SQL.toCodeSet(expressions).chooseQuery(query.getDialect());
    }

    public String getGenericExpression() {
	for (SQL sql : expressions) {
	    if ("generic".equals(sql.getDialect().name())) {
		return sql.getCdata();
	    }
	}
	return expressions.get(0).getCdata();
    }

    public String getTableAlias() {
	return null;
    }

    public int hashCode() {
	int h = 17;

	for (int i = 0; i < expressions.size(); i++) {
	    h = (37 * h) + expressions.get(i).hashCode();
	}

	return h;
    }

    public boolean equals(Object obj) {
	if (!(obj instanceof ExpressionView)) {
	    return false;
	}

	ExpressionView that = (ExpressionView) obj;

	if (this.expressions.size() != that.expressions.size()) {
	    return false;
	}

	for (int i = 0; i < expressions.size(); i++) {
	    if (!this.expressions.get(i).equals(that.expressions.get(i))) {
		return false;
	    }
	}

	return true;
    }

    // END pass-through code block ---
}
