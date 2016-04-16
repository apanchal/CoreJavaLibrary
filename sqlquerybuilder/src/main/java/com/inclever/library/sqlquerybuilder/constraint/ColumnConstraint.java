package com.inclever.library.sqlquerybuilder.constraint;

import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.util.Outputable;

public interface ColumnConstraint extends Outputable {
    ColumnConstraintType getConstraintType();

    void addColumnToConstraintList(Column column);
}
