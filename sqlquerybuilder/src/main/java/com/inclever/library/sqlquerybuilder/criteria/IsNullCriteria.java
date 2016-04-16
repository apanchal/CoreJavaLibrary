package com.inclever.library.sqlquerybuilder.criteria;

import java.util.Set;

import com.inclever.library.sqlquerybuilder.core.Matchable;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Output;

public class IsNullCriteria implements Criteria {
    private final Matchable matched;

    public IsNullCriteria(Matchable matched) {
        this.matched = matched;
    }

    @Override
    public void write(Output out) {
        matched.write(out);
        out.print(" IS NULL");
    }

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
        matched.addReferencedTablesTo(tables);
    }
}
