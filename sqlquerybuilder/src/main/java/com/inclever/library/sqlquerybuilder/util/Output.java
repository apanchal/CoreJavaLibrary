package com.inclever.library.sqlquerybuilder.util;

import com.inclever.library.common.StringPool;

/**
 * The Output is where the elements of the query output their bits of SQL to.
 *
 */
public class Output {

    private StringBuilder result = new StringBuilder(500);

    private StringBuilder currentIndent = new StringBuilder(500);

    private boolean newLineComing;

    private final String indent;

    /**
     * @param indent
     *            String to be used for indenting (e.g. "", "  ", "       ",
     *            "\t")
     */
    public Output(String indent) {
        this.indent = indent;
    }

    @Override
    public String toString() {
        return result.toString();
    }

    public Output print(Object o) {
        writeNewLineIfNeeded();
        result.append(o);
        return this;
    }

    public Output print(char c) {
        writeNewLineIfNeeded();
        result.append(c);
        return this;
    }

    public Output println(Object o) {
        writeNewLineIfNeeded();
        result.append(o);
        newLineComing = true;
        return this;
    }

    public Output println() {
        newLineComing = true;
        return this;
    }

    public void indent() {
        currentIndent.append(indent);
    }

    public void unindent() {
        currentIndent.setLength(currentIndent.length() - indent.length());
    }

    private void writeNewLineIfNeeded() {
        if (newLineComing) {
            result.append(StringPool.NEW_LINE).append(currentIndent);
            newLineComing = false;
        }
    }
}
