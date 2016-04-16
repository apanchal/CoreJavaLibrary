package com.inclever.library.sqlquerybuilder.dbobjects;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.core.Aggerator;
import com.inclever.library.sqlquerybuilder.core.Projection;
import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.ToStringer;

public class AggerateColumn extends Projection implements IColumn {

    private Aggerator aggerator = null;

    private final Table table;

    private final String name;

    private final String alias;

    /**
     * 
     * @param table
     * @param name
     * @param alias
     * @param aggerator
     */
    public AggerateColumn(Table table, String name, String alias, Aggerator aggerator) {
        super(table);
        this.table = table;
        this.name = name;
        this.alias = alias;
        this.aggerator = aggerator;
    }

    /**
     * 
     * @param table
     * @param columnName
     * @param aggerator
     */
    public AggerateColumn(Table table, String columnName, Aggerator aggerator) {
        this(table, columnName, null, aggerator);
    }

    @Override
    public Table getTable() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public Aggerator getAggerator() {
        return aggerator;
    }

    @Override
    public void write(Output out) {
        // Table Name to be used
        String tableName;

        if (table.hasAlias()) {
            tableName = table.getAlias();
        } else {
            tableName = table.getTableName();
        }
        out.print(aggerator.getAggName()).print(StringPool.OPEN_PARENTHESIS);
        out.print(tableName).print(StringPool.DOT).print(getName()).print(StringPool.CLOSE_PARENTHESIS);

        // Append Alias Name
        if (hasAlias()) {
            out.print(StringPool.SPACE).print("AS").print(StringPool.SPACE).print(StringPool.QUOTE).print(getAlias())
                    .print(StringPool.QUOTE);
        }
    }

    @Override
    public String toString() {
        return ToStringer.toString(this);
    }

    /**
     * Whether this Column has an alias assigned.
     */
    private boolean hasAlias() {
        return alias != null;
    }

}
