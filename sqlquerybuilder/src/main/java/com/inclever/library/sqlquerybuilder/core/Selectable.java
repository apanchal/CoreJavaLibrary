package com.inclever.library.sqlquerybuilder.core;

import java.util.Set;

import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Outputable;

/**
 * Something that can be returned from a select query
 * 
 */
public interface Selectable extends Outputable {
    void addReferencedTablesTo(Set<Table> tables);
}
