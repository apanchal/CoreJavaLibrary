package com.inclever.library.sqlquerybuilder.literal;

import java.math.BigDecimal;

/**
 */
public class BigDecimalLiteral extends LiteralWithSameRepresentationInJavaAndSql {

    public BigDecimalLiteral(BigDecimal literalValue) {
        super(literalValue);
    }
}
