package com.inclever.library.sqlquerybuilder.exception;

/**
 * Indicates that a sql builder query is not valid. This is a runtime exception
 * because generally this exception indicates a programmer error, and therefore
 * would not be caught or handled in any way.
 * 
 * @author Ashish Panchal
 */
public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = -2933877497839744427L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

}
