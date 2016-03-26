/*

 */

package com.inclever.library.exception;

public class NoMapperConfiguredException extends RuntimeException {

    private static final long serialVersionUID = 6805704000126104818L;

    public NoMapperConfiguredException(String message) {
        super(message);
    }
}
