package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import mondrian.rolap.sql.SqlQuery;

public abstract class Expression {
    public Expression() {
    }

    public abstract String getExpression(SqlQuery query);

    public abstract String getGenericExpression();

    public abstract String getTableAlias();

}
