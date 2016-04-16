package com.inclever.library.sqlquerybuilder.dbobjects;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.core.Projection;
import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.ToStringer;

public class SelectColumn extends Projection implements /* Matchable, */IColumn {

    private final Table table;

    private final String name;

    private String alias;

    /**
     * 
     * @param table
     * @param name
     * @param alias
     */
    public SelectColumn(Table table, String name, String alias) {
        super(table);
        this.table = table;
        this.name = name;
        this.alias = alias;
    }

    /**
     * 
     * @param table
     * @param name
     */
    public SelectColumn(Table table, String name) {
        this(table, name, null);
    }

    /**
     * 
     */
    @Override
    public Table getTable() {
        return table;
    }

    /**
     * 
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public String getAlias() {
        return alias;
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

        out.print(tableName).print(StringPool.DOT).print(getName());

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
