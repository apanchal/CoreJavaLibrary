/**
 * 
 */
package com.inclever.library.sqlquerybuilder.constraint;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * @author Ashish
 *
 */
public class NotNullConstraint implements ColumnConstraint {

    /*
     * (non-Javadoc)
     * 
     * @see com.library.querybuilder.dbobjects.ColumnConstraint#
     * addColumnToConstraintList (com.library.querybuilder.dbobjects.Column)
     */
    @Override
    public void addColumnToConstraintList(Column column) {
        throw new UnsupportedOperationException("Operation Not Supported.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.library.querybuilder.dbobjects.ColumnConstraint#getConstraintType()
     */
    @Override
    public ColumnConstraintType getConstraintType() {
        return ColumnConstraintType.NOT_NULL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.library.common.util.Outputable#write(com.library.common.util.Output)
     */
    @Override
    public void write(Output out) {
        out.print(StringPool.SPACE).print("NOT NULL").print(StringPool.SPACE);
    }

}
