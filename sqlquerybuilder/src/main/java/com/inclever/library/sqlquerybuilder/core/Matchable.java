package com.inclever.library.sqlquerybuilder.core;

import java.util.Set;

import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Outputable;

/**
 * Something that can be part of a match expression in a where clause
 * 
 */
public interface Matchable extends Outputable {
    void addReferencedTablesTo(Set<Table> tables);
}
