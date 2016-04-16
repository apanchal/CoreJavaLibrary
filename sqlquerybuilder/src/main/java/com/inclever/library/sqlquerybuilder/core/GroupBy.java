package com.inclever.library.sqlquerybuilder.core;

import com.inclever.library.sqlquerybuilder.dbobjects.SelectColumn;
import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.Outputable;

public class GroupBy implements Outputable {

    private SelectColumn column;

    public GroupBy(SelectColumn column) {
        this.column = column;
    }

    public Projection getColumn() {
        return column;
    }

    @Override
    public void write(Output out) {
        String tableName;
        if (column.getTable().getAlias() != null && column.getTable().getAlias().trim().length() != 0) {
            tableName = column.getTable().getAlias();
        } else {
            tableName = column.getTable().getTableName();
        }
        out.print(tableName).print('.').print(column.getName());

    }

}
