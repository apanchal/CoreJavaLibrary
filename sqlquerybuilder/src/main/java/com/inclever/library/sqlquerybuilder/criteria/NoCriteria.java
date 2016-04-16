package com.inclever.library.sqlquerybuilder.criteria;

import java.util.Set;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * Class NoCriteria is a Criteria that represents an absent operand in an SQL
 * predicate expression so that one may represent a unary operator (for example,
 * {@link NOT}) using a binary operator derived from a {@link BaseLogicGroup}).
 * 
 */
public class NoCriteria implements Criteria {
    /**
     * Writes an empty criteria (single space) to the given output stream.
     * 
     * @see com.nividous.library.sqlquerybuilder.criteria#write(com.nividous.library.sqlquerybuilder.util.Output)
     */
    @Override
    public void write(Output out) {
        out.print(StringPool.SPACE);

    }

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
        throw new UnsupportedOperationException("Operation Not Supported!!");
    }
}
