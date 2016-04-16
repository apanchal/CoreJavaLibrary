package com.inclever.library.sqlquerybuilder.dbobjects;

import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.Outputable;

public class CreateColumn implements Outputable, IColumn {

    private final Table table;

    private final String name;

    public CreateColumn(Table table, String name) {
        this.table = table;
        this.name = name;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void write(Output out) {
        throw new UnsupportedOperationException("Operation Not Supported.");

    }

}
