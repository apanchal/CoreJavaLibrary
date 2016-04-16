package com.inclever.library.sqlquerybuilder.literal;

import com.inclever.library.sqlquerybuilder.util.Output;

public class StringLiteral extends Literal {
    private final String literalValue;

    public StringLiteral(String literalValue) {
        this.literalValue = literalValue;
    }

    @Override
    public void write(Output out) {
        out.print(quote(literalValue));
    }

    protected String quote(String s) {
        if (s == null)
            return "null";

        StringBuilder str = new StringBuilder();
        str.append('\'');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\\' || s.charAt(i) == '\"' || s.charAt(i) == '\'') {
                str.append('\\');
            }
            str.append(s.charAt(i));
        }
        str.append('\'');
        return str.toString();
    }
}
