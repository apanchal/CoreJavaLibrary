package com.inclever.library.sqlquerybuilder.query.test;

import org.junit.Test;

import com.inclever.library.sqlquerybuilder.criteria.AND;
import com.inclever.library.sqlquerybuilder.criteria.Criteria;
import com.inclever.library.sqlquerybuilder.criteria.InCriteria;
import com.inclever.library.sqlquerybuilder.criteria.MatchCriteria;
import com.inclever.library.sqlquerybuilder.criteria.MatchCriteriaEnum;
import com.inclever.library.sqlquerybuilder.criteria.OR;
import com.inclever.library.sqlquerybuilder.dbobjects.SelectColumn;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.query.SelectQuery;

public class SelectWithJoin {

    @Test
    public void joinOnForeignKeyRelationship() {
        Table employee = new Table("employee", "emp");
        Table departments = new Table("departments", "dept");

        SelectQuery select = new SelectQuery(); // base table

        select.selectColumn(new SelectColumn(employee, "firstname"));
        select.selectColumn(new SelectColumn(departments, "director"));

        select.addTableJoin(employee, "departmentID", departments, "id");

        System.out.println("Select Query:\n" + select);
        System.out.println("--------------------");

    }

    @Test
    public void joinOnComparison() {
        Table invoices = new Table("invoices");
        Table taxPaymentDate = new Table("tax_payment_date");

        SelectQuery select = new SelectQuery(); // base table

        select.selectColumn(new SelectColumn(invoices, "number"));

        select.addTableJoin(invoices, "date", MatchCriteriaEnum.GREATER, taxPaymentDate, "date");

        System.out.println("Select Query:\n" + select);
        System.out.println("--------------------");
    }

    @Test
    public void doNotHaveToExplicitlyJoinTables() {
        Table invoices = new Table("invoices");
        Table taxPaymentDate = new Table("tax_payment_date");

        SelectQuery select = new SelectQuery(); // base table

        select.selectColumn(new SelectColumn(invoices, "number"));
        select.addCriteria(new MatchCriteria(invoices.getColumn("date"), MatchCriteriaEnum.GREATER,
                taxPaymentDate.getColumn("date")));

        System.out.println("Select Query:\n" + select);
        System.out.println("--------------------");
    }

    @Test
    public void tableAliases() {
        Table employees = new Table("people", "employees");
        Table managers = new Table("people", "managers");

        SelectQuery select = new SelectQuery(); // base table

        select.selectColumn(new SelectColumn(employees, "firstname"));
        select.selectColumn(new SelectColumn(managers, "firstname"));

        select.addTableJoin(employees, "managerID", managers, "id");

        System.out.println("Select Query:\n" + select);
        System.out.println("--------------------");
    }

    @Test
    public void orAnd() {
        Table user = new Table("user");

        SelectQuery select = new SelectQuery();

        select.selectColumn(new SelectColumn(user, "name"));

        Criteria name = new MatchCriteria(user, "name", MatchCriteriaEnum.LIKE, "a%");
        Criteria id = new MatchCriteria(user, "id", MatchCriteriaEnum.EQUALS, 12345);
        Criteria feet = new MatchCriteria(user, "feet", MatchCriteriaEnum.EQUALS, "smelly");

        select.addCriteria(new OR(name, new AND(id, feet)));

        System.out.println("Select Query:\n" + select);
        System.out.println("--------------------");
    }

    @Test
    public void subSelect() {
        // basic query
        SelectQuery select = new SelectQuery();

        // add columns
        Table orders = new Table("orders_table");
        select.selectColumn(new SelectColumn(orders, "id"));
        select.selectColumn(new SelectColumn(orders, "total_price"));

        // matches
        select.addCriteria(new MatchCriteria(orders, "status", MatchCriteriaEnum.EQUALS, "processed"));
        select.addCriteria(new MatchCriteria(orders, "items", MatchCriteriaEnum.LESS, 5));

        // IN...
        select.addCriteria(new InCriteria(orders, "delivery", new String[] { "post", "fedex", "goat" }));

        // join
        Table warehouses = new Table("warehouses_table");
        select.addTableJoin(orders, "warehouse_id", warehouses, "id");

        // use joined table
        select.selectColumn(new SelectColumn(warehouses, "location"));
        select.addCriteria(new MatchCriteria(warehouses, "size", MatchCriteriaEnum.EQUALS, "big"));

        // build subselect query
        SelectQuery subSelect = new SelectQuery();
        Table offers = new Table("offers_table");
        subSelect.selectColumn(new SelectColumn(offers, "location"));
        subSelect.addCriteria(new MatchCriteria(offers, "valid", MatchCriteriaEnum.EQUALS, true));

        // add subselect to original query
        select.addCriteria(new InCriteria(warehouses, "location", subSelect));

        System.out.println("Select Query:\n" + select);
        System.out.println("--------------------");
    }

}
