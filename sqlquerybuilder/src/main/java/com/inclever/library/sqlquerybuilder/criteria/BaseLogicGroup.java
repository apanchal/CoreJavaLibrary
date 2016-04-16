package com.inclever.library.sqlquerybuilder.criteria;

import java.util.Set;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * See OR and AND
 * 
 */
public abstract class BaseLogicGroup implements Criteria {
    private String operator;
    private Criteria left;
    private Criteria right;

    public BaseLogicGroup(String operator, Criteria left, Criteria right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void write(Output out) {
        out.print("( ");
        left.write(out);
        out.print(StringPool.SPACE).print(operator).print(StringPool.SPACE);
        right.write(out);
        out.print(" )");
    }

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
        left.addReferencedTablesTo(tables);
        right.addReferencedTablesTo(tables);
    }
}
