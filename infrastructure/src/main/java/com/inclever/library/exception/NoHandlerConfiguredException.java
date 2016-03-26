/*

 */

package com.inclever.library.exception;

public class NoHandlerConfiguredException extends RuntimeException {

    private static final long serialVersionUID = -9134851328612748928L;

    public NoHandlerConfiguredException(String message) {
        super(message);
    }
}
