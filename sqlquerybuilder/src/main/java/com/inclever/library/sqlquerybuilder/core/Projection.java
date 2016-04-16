package com.inclever.library.sqlquerybuilder.core;

import java.util.Set;

import com.inclever.library.sqlquerybuilder.dbobjects.Table;

/**
 * What can be selected from a table.
 * 
 */
public abstract class Projection implements Selectable {
    private final Table table;

    public Projection(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
        tables.add(table);
    }
}
