package com.inclever.library.sqlquerybuilder.constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * Ensures that all values in a column are different.
 * 
 * @author apanchal
 *
 */
public class UniqueConstraint implements ColumnConstraint {
    private final String constraintName;
    private final List<Column> uniqueColList = new ArrayList<>();

    public UniqueConstraint(String constraintName, Column... uniqueColumn) {
        this.constraintName = constraintName;
        uniqueColList.addAll(Arrays.asList(uniqueColumn));
    }

    @Override
    public void write(Output out) {
        out.print("CONSTRAINT").print(StringPool.SPACE).print(getConstraintName()).print(StringPool.SPACE)
                .print("UNIQUE").print(StringPool.SPACE).print(StringPool.OPEN_PARENTHESIS);
        printUniqueColList(out);
        out.print(StringPool.CLOSE_PARENTHESIS);
    }

    public String getConstraintName() {
        return constraintName;
    }

    public List<Column> getUniqueColList() {
        return uniqueColList;
    }

    @Override
    public ColumnConstraintType getConstraintType() {
        return ColumnConstraintType.UNIQUE;
    }

    @Override
    public void addColumnToConstraintList(Column uniquecolumn) {
        uniqueColList.add(uniquecolumn);
    }

    private void printUniqueColList(Output out) {
        if (uniqueColList != null && !uniqueColList.isEmpty()) {
            int size = uniqueColList.size();
            for (Column col : uniqueColList) {
                out.print(col.getName());
                if (size != 1) {
                    out.print(StringPool.COMMA);
                }
                size--;
            }
        }
    }
}
