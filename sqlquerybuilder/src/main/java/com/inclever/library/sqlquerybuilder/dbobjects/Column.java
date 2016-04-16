package com.inclever.library.sqlquerybuilder.dbobjects;

import java.util.Set;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.core.Matchable;
import com.inclever.library.sqlquerybuilder.util.Output;

public class Column implements IColumn, Matchable {

    private final Table table;

    private final String name;

    public Column(Table table, String name) {
        this.table = table;
        this.name = name;
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

    }

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
        tables.add(table);

    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public String getName() {
        return name;
    }

}
