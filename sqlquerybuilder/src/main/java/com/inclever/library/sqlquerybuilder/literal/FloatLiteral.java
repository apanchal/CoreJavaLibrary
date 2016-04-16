package com.inclever.library.sqlquerybuilder.literal;

/**
 */
public class FloatLiteral extends LiteralWithSameRepresentationInJavaAndSql {
    public FloatLiteral(double literalValue) {
        super(new Double(literalValue));
    }
}
