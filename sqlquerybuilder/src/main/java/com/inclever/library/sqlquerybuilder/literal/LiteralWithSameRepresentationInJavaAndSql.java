package com.inclever.library.sqlquerybuilder.literal;

import com.inclever.library.sqlquerybuilder.util.Output;

/**
 */
public abstract class LiteralWithSameRepresentationInJavaAndSql extends Literal {
    private final Object literalValue;

    protected LiteralWithSameRepresentationInJavaAndSql(Object literalValue) {
        this.literalValue = literalValue;
    }

    @Override
    public void write(Output out) {
        out.print(literalValue);
    }
}
