package com.inclever.library.sqlquerybuilder.criteria;

import java.util.Set;

import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.Outputable;

/**

 */
public interface Criteria extends Outputable {

    @Override
    void write(Output out);

    void addReferencedTablesTo(Set<Table> tables);
}
