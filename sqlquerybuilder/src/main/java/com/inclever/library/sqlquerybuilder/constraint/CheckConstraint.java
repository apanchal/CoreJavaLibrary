package com.inclever.library.sqlquerybuilder.constraint;

import java.util.ArrayList;
import java.util.List;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * Makes sure that all values in a column satisfy certain criteria.
 * 
 * @author apanchal
 *
 */
public class CheckConstraint implements ColumnConstraint {
    private final String constraintName;
    private final List<Column> checkColList = new ArrayList<>();
    private final String checkExpression;

    public CheckConstraint(String constraintName, String checkExpression, Column checkColumn) {
        this.constraintName = constraintName;
        this.checkExpression = checkExpression;
        checkColList.add(checkColumn);
    }

    @Override
    public void write(Output out) {
        out.print("CONSTRAINT").print(StringPool.SPACE).print(getConstraintName()).print(StringPool.SPACE)
                .print("CHECK").print(StringPool.SPACE).print(StringPool.OPEN_PARENTHESIS);
        printCheckColList(out);
        out.print(StringPool.CLOSE_PARENTHESIS);
    }

    public String getConstraintName() {
        return constraintName;
    }

    public List<Column> getCheckColList() {
        return checkColList;
    }

    @Override
    public ColumnConstraintType getConstraintType() {
        return ColumnConstraintType.CHECK;
    }

    @Override
    public void addColumnToConstraintList(Column primaryKeycolumn) {
        checkColList.add(primaryKeycolumn);
    }

    private void printCheckColList(Output out) {
        if (!checkColList.isEmpty()) {
            int size = checkColList.size();
            for (Column col : checkColList) {
                out.print(col.getName()).print(StringPool.SPACE).print(checkExpression);
                if (size != 1) {
                    out.print(StringPool.SPACE).print("AND").print(StringPool.SPACE);
                }
                size--;
            }
        }
    }

    public String getCheckExpression() {
        return checkExpression;
    }
}
