package com.inclever.library.sqlquerybuilder.literal;

import java.util.Set;

import com.inclever.library.sqlquerybuilder.core.Matchable;
import com.inclever.library.sqlquerybuilder.core.Selectable;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Outputable;

/**
 * A literal value, such as a number, string or boolean.
 * 
 * 
 */
public abstract class Literal implements Outputable, Matchable, Selectable {

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
    }
}
