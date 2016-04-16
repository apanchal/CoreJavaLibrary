package com.inclever.library.sqlquerybuilder.literal;

/**
 * b
 */
public class IntegerLiteral extends LiteralWithSameRepresentationInJavaAndSql {
    public IntegerLiteral(long literalValue) {
        super(new Long(literalValue));
    }
}
