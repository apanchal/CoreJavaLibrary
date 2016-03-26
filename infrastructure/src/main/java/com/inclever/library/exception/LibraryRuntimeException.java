/**
 * 
 */
package com.inclever.library.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import com.google.common.base.Throwables;
import com.inclever.library.common.StringPool;
import com.inclever.library.common.SystemPropertiesPool;
import com.inclever.library.exception.i18n.ExceptionMessageGenerator;

/**
 * <p>
 * <b>Purpose</b>: Any exception raised by any module of INCLever modules should
 * be a subclass of this exception class.
 * 
 * @author apanchal@inclever.com
 * 
 */
public abstract class LibraryRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 4389953714898806015L;

    protected Throwable internalException;

    protected static Boolean shouldPrintInternalException = null;

    protected String indentationString;

    protected int errorCode = -1;

    // avoid logging an exception twice
    protected boolean hasBeenLogged;

    /**
     * INTERNAL: Return a new exception.
     */
    public LibraryRuntimeException() {
        this(StringPool.SPACE);
    }

    public LibraryRuntimeException(Throwable throwable) {
        super(throwable);
    }

    /**
     * NividousException exception should only be thrown by BusinessInsight.
     */
    public LibraryRuntimeException(String theMessage) {
        super(theMessage);
        this.indentationString = StringPool.SPACE;
        hasBeenLogged = false;
    }

    /**
     * NividousException exception should only be thrown by BusinessInsight.
     */
    public LibraryRuntimeException(String message, Throwable internalException) {
        this(message);
        setInternalException(internalException);
    }

    public abstract String getModuleName();

    /**
     * Return the exception error code.
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Used to print things nicely in the testing tool.
     */
    public String getIndentationString() {
        return indentationString;
    }

    /**
     * Return the internal native exception. EclipseLink frequently catches Java
     * exceptions and wraps them in its own exception classes to provide more
     * information. The internal exception can still be accessed if required.
     */
    public Throwable getInternalException() {
        return internalException;
    }

    /**
     * Return the exception error message. INCLeverException error messages are
     * multi-line so that detail descriptions of the exception are given.
     */
    public String getMessage() {
        StringWriter writer = new StringWriter(100);

        // Avoid printing internal exception error message twice.
        if ((getInternalException() == null)
                || ((super.getMessage() != null) && !super.getMessage().equals(getInternalException().toString()))) {
            writer.write(SystemPropertiesPool.getLineSeparator());
            // writer.write(getIndentationString());
            writer.write(ExceptionMessageGenerator.getHeader("DescriptionHeader"));
            writer.write(super.getMessage());
        }

        if (getInternalException() != null) {
            writer.write(SystemPropertiesPool.getLineSeparator());
            // writer.write(getIndentationString());
            writer.write(ExceptionMessageGenerator.getHeader("InternalExceptionHeader"));
            writer.write(getInternalException().toString());

            writer.write(logIfSQLException(getInternalException()));

            if ((getInternalException() instanceof java.lang.reflect.InvocationTargetException)
                    && ((((java.lang.reflect.InvocationTargetException) getInternalException())
                            .getTargetException()) != null)) {
                writer.write(SystemPropertiesPool.getLineSeparator());
                // writer.write(getIndentationString());
                writer.write(ExceptionMessageGenerator.getHeader("TargetInvocationExceptionHeader"));
                writer.write(((java.lang.reflect.InvocationTargetException) getInternalException()).getTargetException()
                        .toString());
            }
        }

        return writer.toString();
    }

    /**
     * Return if this exception has been logged to avoid being logged more than
     * once.
     */
    public boolean hasBeenLogged() {
        return hasBeenLogged;
    }

    /**
     * Print both the normal and internal stack traces.
     */
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * Print both the normal and internal stack traces.
     */
    public void printStackTrace(PrintStream outStream) {
        printStackTrace(new PrintWriter(outStream));
    }

    /**
     * PUBLIC: Print both the normal and internal stack traces.
     */
    public void printStackTrace(PrintWriter writer) {
        writer.write(ExceptionMessageGenerator.getHeader("LocalExceptionStackHeader"));
        // writer.write(SystemPropertiesPool.getLineSeparator());
        super.printStackTrace(writer);

        if ((getInternalException() != null) && shouldPrintInternalException()) {
            writer.write(ExceptionMessageGenerator.getHeader("InternalExceptionStackHeader"));
            writer.write(SystemPropertiesPool.getLineSeparator());
            getInternalException().printStackTrace(writer);

            if ((getInternalException() instanceof java.lang.reflect.InvocationTargetException)
                    && ((((java.lang.reflect.InvocationTargetException) getInternalException())
                            .getTargetException()) != null)) {
                writer.write(ExceptionMessageGenerator.getHeader("TargetInvocationExceptionStackHeader"));
                writer.write(SystemPropertiesPool.getLineSeparator());
                ((java.lang.reflect.InvocationTargetException) getInternalException()).getTargetException()
                        .printStackTrace(writer);
            }
        }
        writer.flush();
    }

    /**
     * Sets error code
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Set this flag to avoid logging an exception more than once.
     */
    public void setHasBeenLogged(boolean logged) {
        this.hasBeenLogged = logged;
    }

    /**
     * Used to print things nicely in the testing tool.
     */
    public void setIndentationString(String indentationString) {
        this.indentationString = indentationString;
    }

    /**
     * Used to specify the internal exception.
     */
    public void setInternalException(Throwable exception) {
        internalException = exception;
        if (getCause() == null) {
            initCause(exception);
        }
    }

    /**
     * Allows overriding of CoreLibrary's exception chaining detection.
     * 
     * @param booleam
     *            printException - If printException is true, the stored
     *            Internal exception will be included in a stack trace or in the
     *            exception message of a INCLeverException. If printException is
     *            false, the EclipseLink-stored Internal Exception will not be
     *            included in the stack trace or the exception message of
     *            INCLeverExceptions
     */
    public static void setShouldPrintInternalException(boolean printException) {
        shouldPrintInternalException = Boolean.valueOf(printException);
    }

    /**
     * Check to see if the stored internal exception should be printed in this a
     * INCLeverException's stack trace. This method will check the static
     * ShouldPrintInternalException variable.
     */
    public static boolean shouldPrintInternalException() {
        if (shouldPrintInternalException == null) {
            shouldPrintInternalException = Boolean.FALSE;
        }
        return shouldPrintInternalException.booleanValue();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(SystemPropertiesPool.getLineSeparator())
                // .append(getIndentationString())
                .append(ExceptionMessageGenerator.getHeader("ExceptionHeader"))
                // Exception
                .append(StringPool.SPACE).append(getModuleName())
                // (<MODULE_NAME> - <VERISON>)
                .append(StringPool.SPACE).append(StringPool.OPENING_BRACKET)
                // [
                .append("INCLever - " + getErrorCode())
                // INClever error code
                .append(StringPool.CLOSE_BRACKET)
                // ]
                .append(StringPool.COLON)
                // :
                .append(StringPool.SPACE).append(getClass().getName()).append(getMessage());
        return builder.toString();

    }

    private String logIfSQLException(Throwable originalEx) {
        Throwable rootCause = Throwables.getRootCause(originalEx);
        StringBuffer buffer = new StringBuffer();
        Throwable cause = rootCause;

        // keep looping to check the next chained exception
        while (cause != null) {
            // Only check if the exception is an SQLException
            if (SQLException.class.isAssignableFrom(rootCause.getClass())) {

                SQLException sqle = (SQLException) cause;
                buffer.append(SystemPropertiesPool.getLineSeparator())
                        // .append(getIndentationString())

                        .append(ExceptionMessageGenerator.getHeader("SQLException")).append(sqle.getClass().getName())
                        .append(SystemPropertiesPool.getLineSeparator())

                        // .append(getIndentationString())
                        .append(ExceptionMessageGenerator.getHeader("VendorSQLErrorCodeHeader"))
                        .append(sqle.getErrorCode()).append(SystemPropertiesPool.getLineSeparator())

                        // .append(getIndentationString())
                        .append(ExceptionMessageGenerator.getHeader("SQLSTATEHeader")).append(sqle.getSQLState())
                        .append(SystemPropertiesPool.getLineSeparator())

                        // .append(getIndentationString())
                        .append(ExceptionMessageGenerator.getHeader("SQLMessageHeader")).append(sqle.getMessage())
                        .append(SystemPropertiesPool.getLineSeparator());
            }

            // Get the next chained exception
            Throwable newCause = cause.getCause();

            // Safe-guard check to avoid indefinite loop if the exception chains
            // itself
            if (newCause == cause) {
                break;
            } else {
                cause = newCause;
            }
        }

        return buffer.toString();
    }

}
