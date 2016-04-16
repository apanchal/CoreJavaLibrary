package com.inclever.library.sqlquerybuilder.query.test;

import org.junit.Test;

import com.inclever.library.sqlquerybuilder.core.Aggerator;
import com.inclever.library.sqlquerybuilder.dbobjects.AggerateColumn;
import com.inclever.library.sqlquerybuilder.dbobjects.SelectColumn;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.query.SelectQuery;

public class SimpleSelect001 {

    @Test
    public void testSimpleColumn() {
        Table table = new Table("employee", "emp");

        SelectQuery selectQuery = new SelectQuery();

        selectQuery.selectColumn(new SelectColumn(table, "first_name", "My First Name"));
        selectQuery.selectColumn(new SelectColumn(table, "last_name"));

        selectQuery.addOrder(table, "age", false);

        System.out.println("Simple Select:\n" + selectQuery);
        System.out.println("--------------------");
    }

    @Test
    public void testAggColumn() {
        Table table = new Table("employee", "emp");

        SelectQuery selectQuery = new SelectQuery();

        selectQuery.selectColumn(new SelectColumn(table, "first_name", "My First Name"));
        selectQuery.selectColumn(new SelectColumn(table, "last_name"));
        selectQuery.selectAggColumn(new AggerateColumn(table, "salary", "Pay Check", Aggerator.AVG));

        selectQuery.addOrder(table, "age", false);

        System.out.println("Agg. Select:\n" + selectQuery);
        System.out.println("--------------------");
    }

    @Test
    public void testnullColumn() {
        try {
            Table table = new Table("employee", "emp");

            SelectQuery selectQuery = new SelectQuery();

            selectQuery.selectColumn(null);
            selectQuery.selectColumn(null);

            selectQuery.addOrder(table, "age", false);

            System.out.println("Null Select:\n" + selectQuery);
            System.out.println("--------------------");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
