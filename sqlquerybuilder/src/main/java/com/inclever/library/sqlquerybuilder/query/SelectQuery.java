package com.inclever.library.sqlquerybuilder.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

import com.inclever.library.common.StringPool;
import com.inclever.library.logging.LogManagerFactory;
import com.inclever.library.sqlquerybuilder.core.GroupBy;
import com.inclever.library.sqlquerybuilder.core.Order;
import com.inclever.library.sqlquerybuilder.core.Selectable;
import com.inclever.library.sqlquerybuilder.core.ValueSet;
import com.inclever.library.sqlquerybuilder.criteria.Criteria;
import com.inclever.library.sqlquerybuilder.criteria.MatchCriteria;
import com.inclever.library.sqlquerybuilder.criteria.MatchCriteriaEnum;
import com.inclever.library.sqlquerybuilder.dbobjects.AggerateColumn;
import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.dbobjects.SelectColumn;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.exception.SQLQueryBuilderException;
import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.Outputable;
import com.inclever.library.sqlquerybuilder.util.ToStringer;

public class SelectQuery implements ValueSet {

    /* Logger instance */
    private static final Logger LOG = LogManagerFactory.getInstance().getLogger(SelectQuery.class);

    private final List<Table> tables = new ArrayList<>();

    private boolean isDistinct = false;

    private final List<Selectable> selection = new ArrayList<>();

    private final List<Selectable> aggSelection = new ArrayList<>();

    private final List<Criteria> criteria = new ArrayList<>();

    private final List<Order> order = new ArrayList<>();

    /*
     * Create Select Query
     */
    public SelectQuery() {
        super();
    }

    /**
     * is distinct
     * 
     * @return
     */
    public boolean isDistinct() {
        return isDistinct;
    }

    /**
     * set Distinct Column
     * 
     * @param distinct
     */
    public void setDistinct(boolean distinct) {
        isDistinct = distinct;
    }

    /**
     * 
     * @param selectColumn
     */
    public void selectColumn(SelectColumn selectColumn) {
        if (selectColumn == null) {
            LOG.warn("Select Column cannot be null");
            return;
        }
        tables.add(selectColumn.getTable());
        selection.add(selectColumn);
    }

    /**
     * 
     * @param aggerateColumn
     */
    public void selectAggColumn(AggerateColumn aggerateColumn) {
        if (aggerateColumn == null) {
            LOG.warn("Select Agg. Column cannot be null");
            return;
        }
        tables.add(aggerateColumn.getTable());
        selection.add(aggerateColumn);
        aggSelection.add(aggerateColumn);
    }

    public void addFilter(Criteria criteria) {
        this.criteria.add(criteria);
    }

    public void addTableJoin(Table srcTable, String srcColumnname, Table destTable, String destColumnname) {
        Column srcCol = new Column(srcTable, srcColumnname);
        Column destCol = new Column(destTable, destColumnname);
        addCriteria(new MatchCriteria(srcCol, MatchCriteriaEnum.EQUALS, destCol));
    }

    public void addTableJoin(Table srcTable, String srcColumnName, MatchCriteriaEnum operator, Table destTable,
            String destColumnName) {
        Column srcCol = new Column(srcTable, srcColumnName);
        Column destCol = new Column(destTable, destColumnName);
        addCriteria(new MatchCriteria(srcCol, operator, destCol));
    }

    public void addCriteria(Criteria criteria) {
        this.criteria.add(criteria);
    }

    private void addOrder(Order order) {
        this.order.add(order);
    }

    public void addOrder(Table table, String columnname, boolean ascending) {
        SelectColumn col = new SelectColumn(table, columnname, null);
        addOrder(new Order(col, ascending));
    }

    @Override
    public void write(Output out) {
        // Validate that we have something to Select
        if (selection.isEmpty()) {
            throw SQLQueryBuilderException.emptySelect();
        }

        out.println("SELECT ");
        if (isDistinct) {
            out.print(" DISTINCT");
        }
        out.println();

        appendListToQuery(out, selection, ",");
        Set<Table> usedTables = findUsedTables();
        if (!usedTables.isEmpty()) {
            out.println("FROM");
            appendListToQuery(out, usedTables, ",");
        }

        // Add filter
        if (!criteria.isEmpty()) {
            out.println("WHERE");
            appendListToQuery(out, criteria, "AND");
        }
        // Add Group By
        List<GroupBy> groupByColumns = getGroupBy();
        if (!groupByColumns.isEmpty()) {
            out.println("GROUP BY");
            appendListToQuery(out, groupByColumns, ",");
        }

        // Add order
        if (!order.isEmpty()) {
            out.println("ORDER BY");
            appendListToQuery(out, order, ",");
        }
    }

    @Override
    public String toString() {
        return ToStringer.toString(this);
    }

    private List<GroupBy> getGroupBy() {
        List<GroupBy> groupByList = new ArrayList<>();
        // if any Aggregated Column Selected
        if (!aggSelection.isEmpty()) {
            List<Selectable> selectionCopy = new ArrayList<>(selection);
            // Remove Aggregated Column Name, from the Group by Clause
            selectionCopy.removeAll(aggSelection);
            if (!selectionCopy.isEmpty()) {
                for (Selectable column : selectionCopy) {
                    groupByList.add(new GroupBy((SelectColumn) column));
                }
            }
        }

        return groupByList;

    }

    private void appendListToQuery(Output out, Collection<? extends Outputable> things, String seperator) {
        out.indent();
        appendList(out, things, seperator);
        out.unindent();
    }

    /**
     * Iterate through a Collection and append all entries (using .toString())
     * to a StringBuffer.
     */
    private void appendList(Output out, Collection<? extends Outputable> collection, String seperator) {
        Iterator<? extends Outputable> i = collection.iterator();
        boolean hasNext = i.hasNext();

        while (hasNext) {
            Outputable curr = i.next();
            hasNext = i.hasNext();
            curr.write(out);
            out.print(StringPool.SPACE);
            if (hasNext) {
                out.print(seperator);
            }
            out.println();
        }
    }

    /**
     * Find all the tables used in the query (from columns, criteria and order).
     * 
     * @return Set of {@link om.library.querybuilder.dbobjects.Table}s
     */
    private Set<Table> findUsedTables() {
        Set<Table> refTables = new LinkedHashSet<>();
        addReferencedTablesTo(refTables);
        return refTables;
    }

    private void addReferencedTablesTo(Set<Table> tables) {
        for (Selectable s : selection) {
            s.addReferencedTablesTo(tables);
        }
        for (Criteria c : criteria) {
            c.addReferencedTablesTo(tables);
        }
        for (Order o : order) {
            o.addReferencedTablesTo(tables);
        }
    }
}
