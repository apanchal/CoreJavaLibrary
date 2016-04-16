package com.inclever.library.sqlquerybuilder.criteria;

import java.util.Set;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.core.Matchable;
import com.inclever.library.sqlquerybuilder.core.ValueSet;
import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.literal.LiteralValueSet;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * 
 */
public class InCriteria implements Criteria {
    private final Matchable matched;
    private final ValueSet valueSet;

    public InCriteria(Matchable matchable, ValueSet valueSet) {
        this.matched = matchable;
        this.valueSet = valueSet;
    }

    public InCriteria(Matchable column, String... values) {
        this.matched = column;
        this.valueSet = new LiteralValueSet(values);
    }

    public InCriteria(Matchable column, long... values) {
        this.matched = column;
        this.valueSet = new LiteralValueSet(values);
    }

    public InCriteria(Matchable column, double... values) {
        this.matched = column;
        this.valueSet = new LiteralValueSet(values);
    }

    public InCriteria(Table table, String columnname, ValueSet valueSet) {
        this(new Column(table, columnname), valueSet);
    }

    public InCriteria(Table table, String columnname, String[] values) {
        this(new Column(table, columnname), values);
    }

    public InCriteria(Table table, String columnname, double[] values) {
        this(new Column(table, columnname), values);
    }

    public InCriteria(Table table, String columnname, long[] values) {
        this(new Column(table, columnname), values);
    }

    public Matchable getMatched() {
        return matched;
    }

    @Override
    public void write(Output out) {
        matched.write(out);
        out.println(" IN").print(StringPool.SPACE).print(StringPool.OPEN_PARENTHESIS);
        out.indent();
        valueSet.write(out);
        out.println();
        out.unindent();
        out.print(StringPool.CLOSE_PARENTHESIS);
    }

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
        matched.addReferencedTablesTo(tables);
    }
}
