/**
 * 
 */
package com.inclever.library.exception.core;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.inclever.library.common.SystemPropertiesPool;
import com.inclever.library.exception.LibraryException;
import com.inclever.library.exception.LibraryRuntimeException;
import com.inclever.library.exception.i18n.ExceptionMessageGenerator;
import com.inclever.library.exception.util.Utils;

/**
 * @author Ashish Panchal(aashish.panchal@gmail.com)
 * 
 */
public abstract class AbstractExceptionHandler {

    private static final int INIT_BUFFER_SIZE = 1024;

    private static final String LINE = "--------------------------------------------------------------------------------------\n";

    /**
     * 
     */
    public AbstractExceptionHandler() {
        super();
    }

    /**
     * 
     * @return
     */
    // public abstract String getModuleName();

    /**
     * Gets the detailed message of a <code>BaseAppException</code> including
     * stack trace, userId and errorId information in String form. This detailed
     * message is used as a stack trace for logger file as well as database.
     * 
     * @param throwable
     *            BaseAppException object
     * @param userId
     *            the user for which exception occured.
     * @return the detailed message.
     * 
     */
    protected final String getExceptionLog(Throwable throwable) {
        String detailedMessage;
        if (throwable instanceof LibraryException || throwable instanceof LibraryRuntimeException) {
            LibraryException ie = (LibraryException) throwable;
            detailedMessage = getDetailedMessage(ie);
        } else {
            detailedMessage = getDetailedMessage(throwable);
        }

        StringBuilder lBuffer = new StringBuilder(INIT_BUFFER_SIZE);
        lBuffer.append(detailedMessage);
        lBuffer.append(SystemPropertiesPool.getLineSeparator());
        lBuffer.append(LINE);

        return lBuffer.toString();
    }

    /**
     * Gets the stack trace of a <code>Throwable</code> in String form.
     * 
     * @param a
     *            Throwable object.
     * @return returns the message as String.
     * 
     */
    private String getDetailedMessage(Throwable throwable) {
        StringBuilder msg = new StringBuilder();

        msg.append(SystemPropertiesPool.getLineSeparator());
        msg.append(ExceptionMessageGenerator.getHeader("ExceptionHeader"));
        msg.append(throwable.getClass().getName());
        msg.append(SystemPropertiesPool.getLineSeparator());

        msg.append(ExceptionMessageGenerator.getHeader("DescriptionHeader"));
        msg.append(throwable.getMessage());
        msg.append(SystemPropertiesPool.getLineSeparator());

        msg.append(ExceptionMessageGenerator.getHeader("ExceptionStackTraceHeader"));
        msg.append(Utils.toString(throwable));

        return msg.toString();
    }

    /**
     * Gets the stack trace of a <code>LibraryException</code> in String form.
     * 
     * @param exception
     *            LibraryException object
     * @return returns the detailed message.
     * 
     */
    private String getDetailedMessage(LibraryException ie) {
        StringWriter writer = new StringWriter(INIT_BUFFER_SIZE);
        ie.setShouldPrintInternalException(true);
        ie.printStackTrace(new PrintWriter(writer));
        return new StringBuffer(writer.toString()).toString();
        /*
         * StringBuffer msg = new StringBuffer(INIT_BUFFER_SIZE);
         * 
         * if (ie.getMessage() != null) { // Get Module Name Dynamically add
         * Error Code into Exception
         * msg.append(SystemPropertiesPool.getLineSeparator());
         * 
         * msg.append(NividousExceptionMessageGenerator
         * .getHeader("INCLeverExceptionHeader")); //
         * .concat(String.valueOf(ie.getErrorCode())).concat("] ") //
         * .concat(getModuleName()).concat(":"));
         * 
         * msg.append(ie.getClass().getName()); msg.append(ie.getMessage()); }
         * 
         * msg.append(SystemPropertiesPool.getLineSeparator());
         * msg.append(Utils.toString(ie)); Throwable rootCause = ie.getCause();
         * if (rootCause != null) {
         * msg.append(SystemPropertiesPool.getLineSeparator()); msg.append(
         * "INTERNAL EXCEPTION: "); msg.append(rootCause.toString());
         * msg.append(SystemPropertiesPool.getLineSeparator());
         * msg.append(Utils.toString(rootCause)); } return msg.toString();
         */
    }

    /*
     * protected void logException(Logger logger, String message, Throwable
     * throwable, Object msgParams) { logger.error(formatMessage(message,
     * msgParams)); logger.error(getExceptionLog(throwable)); }
     * 
     * protected void logException(Logger logger, Throwable throwable, Object
     * msgParams) { logger.error(getExceptionLog(throwable)); }
     */

    // protected final String formatMessage(String messageStr, Object... params)
    // {
    // StringBuffer buffer = new StringBuffer();
    // String message = MessageFormat.format(messageStr, params);
    // if (message != null) {
    // buffer.append(MESSAGE);
    // buffer.append(message);
    // buffer.append(SystemPropertiesPool.getLineSeparator());
    // }
    // return buffer.toString();
    // }
    //
    // protected final String formatMessage(Throwable throwable,
    // String messageKey, Object... params) {
    // StringBuffer buffer = new StringBuffer();
    // buffer.append(formatMessage(messageKey, params));
    // buffer.append(getExceptionLog(throwable));
    // buffer.append(SystemPropertiesPool.getLineSeparator());
    // buffer.append(LINE);
    // return buffer.toString();
    // }

}
