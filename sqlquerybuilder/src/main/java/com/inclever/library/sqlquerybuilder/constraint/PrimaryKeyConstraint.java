package com.inclever.library.sqlquerybuilder.constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * Used to uniquely identify a row in the table.
 * 
 * @author apanchal
 *
 */
public class PrimaryKeyConstraint implements ColumnConstraint {
    private final String constraintName;
    private final List<Column> primaryKeyColList = new ArrayList<>();

    public PrimaryKeyConstraint(String constraintName, Column... primaryKeycolumns) {
        this.constraintName = constraintName;
        primaryKeyColList.addAll(Arrays.asList(primaryKeycolumns));

    }

    @Override
    public void write(Output out) {
        out.print("CONSTRAINT").print(StringPool.SPACE).print(getConstraintName()).print(StringPool.SPACE)
                .print("PRIMARY KEY").print(StringPool.SPACE).print(StringPool.OPEN_PARENTHESIS);
        printPrimaryKeyColList(out);
        out.print(StringPool.CLOSE_PARENTHESIS);
    }

    public String getConstraintName() {
        return constraintName;
    }

    public List<Column> getPrimaryKeyColList() {
        return primaryKeyColList;
    }

    @Override
    public ColumnConstraintType getConstraintType() {
        return ColumnConstraintType.PRIMARY_KEY;
    }

    @Override
    public void addColumnToConstraintList(Column primaryKeycolumn) {
        primaryKeyColList.add(primaryKeycolumn);
    }

    private void printPrimaryKeyColList(Output out) {
        if (primaryKeyColList != null && !primaryKeyColList.isEmpty()) {
            int size = primaryKeyColList.size();
            for (Column col : primaryKeyColList) {
                out.print(col.getName());
                if (size != 1) {
                    out.print(StringPool.COMMA);
                }
                size--;
            }
        }
    }
}
