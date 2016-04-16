package com.inclever.library.sqlquerybuilder.constraint;

public enum ColumnConstraintType {
    CHECK("CHECK"), DEFAULT("DEFAULT"), FOREIGN_KEY("FOREIGN_KEY"), NOT_NULL("NOT_NULL"), PRIMARY_KEY(
            "PRIMARY_KEY"), UNIQUE("UNIQUE");

    private final String constraintType;

    private ColumnConstraintType(String constraintClause) {
        constraintType = constraintClause;
    }

    @Override
    public String toString() {
        return constraintType;
    }
}
