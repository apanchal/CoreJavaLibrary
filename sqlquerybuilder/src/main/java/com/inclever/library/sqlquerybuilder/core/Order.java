package com.inclever.library.sqlquerybuilder.core;

import java.util.Set;

import com.inclever.library.sqlquerybuilder.dbobjects.SelectColumn;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.Outputable;

/**
 * ORDER BY clause. See SelectQuery.addOrder(Order).
 * 
 */
public class Order implements Outputable {

    public static final boolean ASCENDING = true;

    public static final boolean DESCENDING = false;

    private SelectColumn column;

    private boolean isAscending;

    /**
     * @param column
     *            Column to order by.
     * @param ascending
     *            Order.ASCENDING or Order.DESCENDING
     */
    public Order(SelectColumn column, boolean isAscending) {
        this.column = column;
        this.isAscending = isAscending;
    }

    public Projection getColumn() {
        return column;
    }

    @Override
    public void write(Output out) {
        column.write(out);
        if (!isAscending) {
            out.print(" DESC");
        }
    }

    public void addReferencedTablesTo(Set<Table> tables) {
        column.addReferencedTablesTo(tables);
    }
}
