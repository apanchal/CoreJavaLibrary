/**
 * 
 */
package com.inclever.library.logging;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;

/**
 * @author apanchal
 * 
 */
public final class SystemOutInterceptor extends PrintStream {

    /** the origin output stream */
    PrintStream orig;

    private static final String errorMessage = "**** DON'T USE System.out.println(). IT's BLOCKED, INSTEAD USE logger.debug() **** ";

    /**
     * Initializes a new instance of the class Intercepter.
     * 
     * @param out
     *            the output stream to be assigned
     */
    protected SystemOutInterceptor(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String x) {
        super.println(errorMessage);
    }

    @Override
    public void println(Object x) {
        super.println(errorMessage);
    }

    @Override
    public void println() {
        super.println(errorMessage);
    }

    @Override
    public void println(boolean x) {
        super.println(errorMessage);
    }

    @Override
    public void println(char x) {
        super.println(errorMessage);
    }

    @Override
    public void println(int x) {
        super.println(errorMessage);
    }

    @Override
    public void println(long x) {
        super.println(errorMessage);
    }

    @Override
    public void println(float x) {
        super.println(errorMessage);
    }

    @Override
    public void println(double x) {
        super.println(errorMessage);
    }

    @Override
    public void println(char[] x) {
        super.println(errorMessage);
    }

    @Override
    public void print(String s) {
        super.print(errorMessage);
    }

    @Override
    public void print(boolean b) {
        super.print(errorMessage);
    }

    @Override
    public void print(char c) {
        super.print(c);
    }

    @Override
    public void print(int i) {
        super.print(i);
    }

    @Override
    public void print(long l) {
        super.print(errorMessage);
    }

    @Override
    public void print(float f) {
        super.print(errorMessage);
    }

    @Override
    public void print(double d) {
        super.print(errorMessage);
    }

    @Override
    public void print(char[] s) {
        super.print(errorMessage);
    }

    @Override
    public void print(Object obj) {
        super.print(errorMessage);
    }

    @Override
    public PrintStream printf(String format, Object... args) {
        super.print(errorMessage);
        return this;
    }

    @Override
    public PrintStream printf(Locale l, String format, Object... args) {
        super.print(errorMessage);
        return this;
    }

    @Override
    public PrintStream format(String format, Object... args) {
        super.print(errorMessage);
        return this;
    }

    @Override
    public PrintStream format(Locale l, String format, Object... args) {
        super.print(errorMessage);
        return this;
    }

    /**
     * Attaches System.out to intercepter.
     */
    public void attachOut() {
        orig = System.out;
        System.setOut(this);
    }

    /**
     * Detaches System.out.
     */
    public void detachOut() {
        if (null != orig) {
            System.setOut(orig);
        }
    }

    /**
     * Attaches System.err to intercepter.
     */
    public void attachErr() {
        orig = System.err;
        System.setErr(this);
    }

    /**
     * Detaches System.err.
     */
    public void detachErr() {
        if (null != orig) {
            System.setErr(orig);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        detachOut();
        super.finalize();
    }

}
