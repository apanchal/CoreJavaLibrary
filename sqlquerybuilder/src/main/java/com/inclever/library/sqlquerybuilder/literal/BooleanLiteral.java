package com.inclever.library.sqlquerybuilder.literal;

/**
 */
public class BooleanLiteral extends LiteralWithSameRepresentationInJavaAndSql {
    public static final BooleanLiteral TRUE = new BooleanLiteral(true);

    public static final BooleanLiteral FALSE = new BooleanLiteral(false);

    public BooleanLiteral(boolean literalValue) {
        this(Boolean.valueOf(literalValue));
    }

    public BooleanLiteral(Boolean literalValue) {
        super(literalValue);
    }
}
