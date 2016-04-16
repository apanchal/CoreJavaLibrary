package com.inclever.library.sqlquerybuilder.util;

/**
 * Utility to quickly grab the complete String from an object that is
 * Outputtable
 *
 */
public class ToStringer {

    public enum Indent {
        SINGLE_SPACE(" "), DOUBLE_SPACE(" "), TAB("\t"), NO_SPACE("");

        private final String indent;

        private Indent(String indent) {
            this.indent = indent;
        }

        public String getIndent() {
            return indent;
        }
    }

    public static String toString(Outputable outputable) {
        Output out = new Output("    ");
        outputable.write(out);
        return out.toString();
    }

    public static String toString(Outputable outputable, Indent indent) {
        Output out = new Output(indent.indent);
        outputable.write(out);
        return out.toString();
    }
}
