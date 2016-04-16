package com.inclever.library.sqlquerybuilder.query.test;

import org.junit.Test;

import com.inclever.library.sqlquerybuilder.criteria.BetweenCriteria;
import com.inclever.library.sqlquerybuilder.criteria.InCriteria;
import com.inclever.library.sqlquerybuilder.criteria.IsNotNullCriteria;
import com.inclever.library.sqlquerybuilder.criteria.IsNullCriteria;
import com.inclever.library.sqlquerybuilder.criteria.MatchCriteria;
import com.inclever.library.sqlquerybuilder.criteria.MatchCriteriaEnum;
import com.inclever.library.sqlquerybuilder.dbobjects.SelectColumn;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.query.SelectQuery;

public class SelectWithWhere002 {

    @Test
    public void whereCriteria() {
        Table employee = new Table("employee");

        SelectQuery select = new SelectQuery();

        select.selectColumn(new SelectColumn(employee, "firstname"));
        select.selectColumn(new SelectColumn(employee, "lastname"));

        select.addCriteria(new MatchCriteria(employee, "height", MatchCriteriaEnum.GREATER, 1.8));
        select.addCriteria(new InCriteria(employee, "department", new String[] { "I.T.", "Cooking" }));
        select.addCriteria(new BetweenCriteria(employee.getColumn("age"), 18, 30));

        System.out.println("Query:\n" + select);
    }

    @Test
    public void nullCriteria() {
        Table employee = new Table("employee");

        SelectQuery select = new SelectQuery();

        select.selectColumn(new SelectColumn(employee, "firstname"));

        select.addCriteria(new IsNullCriteria(employee.getColumn("name")));
        select.addCriteria(new IsNotNullCriteria(employee.getColumn("age")));

        System.out.println("Query:\n" + select);
    }

    @Test
    public void betweenCriteriaWithColumns() {
        Table rivers = new Table("rivers");

        SelectQuery select = new SelectQuery();

        select.selectColumn(new SelectColumn(rivers, "name"));
        select.selectColumn(new SelectColumn(rivers, "level"));

        select.addCriteria(new BetweenCriteria(rivers.getColumn("level"), rivers.getColumn("lower_limit"),
                rivers.getColumn("upper_limit")));

        System.out.println("Query:\n" + select);
    }

}
