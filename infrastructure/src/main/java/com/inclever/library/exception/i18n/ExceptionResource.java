package com.inclever.library.exception.i18n;

import java.util.ListResourceBundle;

/**
 * English ResourceBundle for INCLeverException messages.
 * 
 * @author apanchal
 * @since 2.0.0
 */
public class ExceptionResource extends ListResourceBundle {

    /**
     * Lookup tables for messages
     */
    static final Object[][] contents = {
            { "NoExceptionTranslationForThisLocale", "(There is no English translation for this exception.) {0}" },
            { "ExceptionHeader", "Exception: " }, { "INCLeverExceptionHeader", "Exception" },

            /* For Error Code Header for Library Exception */
            { "INCLeverExceptionErrorCodeHeader", "Exception [INCLever - <ERROR_CODE> ]" },

            { "DescriptionHeader", "Exception Description: " }, { "InternalExceptionHeader", "Internal Exception: " },
            { "ExceptionStackTraceHeader", "Exception StackTrace: " }, { "ErrorCodeHeader", "Error Code: " },
            { "InternalExceptionStackHeader", "Internal Exception StackTrace:" },
            { "LocalExceptionStackHeader", "Local Exception StackTrace:" },
            { "SQLException", "SQL Exception: " }, { "VendorSQLErrorCodeHeader", "SQL Error Code: " },
            { "SQLSTATEHeader", "SQL State: " }, { "SQLMessageHeader", "SQL Error Message: " },
            { "ErrorFormattingMessage", "Error trying to format exception message: {0}  The arguments are: {1}" }

    };

    /**
     * Return the lookup table.
     */
    @Override
    protected final Object[][] getContents() {
        return contents;
    }

}
