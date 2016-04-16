package com.inclever.library.sqlquerybuilder.dbobjects;

import com.inclever.library.sqlquerybuilder.util.Outputable;

public interface TableIf extends Outputable {

    /**
     * @return the alias for this table which <b>should be</b> unique among all
     *         tables in a given query
     */
    public String getAlias();

    /**
     * @return a string which represents an absolute (fully qualified) reference
     *         to this table, suitable for sql statements
     */
    public String getTableName();

}
