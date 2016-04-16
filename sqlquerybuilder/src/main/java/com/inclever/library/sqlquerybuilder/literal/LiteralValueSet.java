package com.inclever.library.sqlquerybuilder.literal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.inclever.library.sqlquerybuilder.core.ValueSet;
import com.inclever.library.sqlquerybuilder.util.Output;

public class LiteralValueSet implements ValueSet {
    private final Collection<Literal> literals;

    public LiteralValueSet(Collection<Literal> literals) {
        this.literals = literals;
    }

    public LiteralValueSet(String... values) {
        this.literals = new ArrayList<>(values.length);
        for (String value : values) {
            literals.add(new StringLiteral(value));
        }
    }

    public LiteralValueSet(long... values) {
        this.literals = new ArrayList<>(values.length);
        for (long value : values) {
            literals.add(new IntegerLiteral(value));
        }
    }

    public LiteralValueSet(double... values) {
        this.literals = new ArrayList<>(values.length);
        for (double value : values) {
            literals.add(new FloatLiteral(value));
        }
    }

    public LiteralValueSet(BigDecimal... values) {
        this.literals = new ArrayList<>(values.length);
        for (BigDecimal value : values) {
            literals.add(new BigDecimalLiteral(value));
        }
    }

    public LiteralValueSet(Date... values) {
        this.literals = new ArrayList<>(values.length);
        for (Date value : values) {
            literals.add(new DateTimeLiteral(value));
        }
    }

    @Override
    public void write(Output out) {
        for (Iterator<Literal> it = literals.iterator(); it.hasNext();) {
            Literal literal = it.next();
            literal.write(out);
            if (it.hasNext()) {
                out.print(", ");
            }
        }
    }
}
