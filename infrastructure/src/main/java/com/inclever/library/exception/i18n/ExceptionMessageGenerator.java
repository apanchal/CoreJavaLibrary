package com.inclever.library.exception.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.slf4j.Logger;

import com.inclever.library.common.StringPool;
import com.inclever.library.common.SystemPropertiesPool;
import com.inclever.library.logging.LogManagerFactory;

/**
 * Utility class to generate exception messages using ResourceBundles. All
 * modules of core library should generates its exception message through this
 * class only.
 * 
 * @author aashish.panchal@gmail.com
 * @since 2.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ExceptionMessageGenerator {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(ExceptionMessageGenerator.class);

    private ExceptionMessageGenerator() {
        super();
    }

    /**
     * Return the message for the given exception class and error number.
     * 
     * @param exceptionResourceBundleclazzName
     * @param errorNumber
     * @param arguments
     * @return
     */
    public static String buildMessage(String exceptionResourceBundleclazzName, int errorNumber, Object[] arguments) {

        String message = StringPool.SPACE;
        ResourceBundle bundle;

        // MessageFormat can't handle null arguments
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] == null) {
                arguments[i] = "null";
            }
        }

        bundle = ResourceBundle.getBundle(exceptionResourceBundleclazzName, Locale.getDefault(),
                ExceptionMessageGenerator.class.getClassLoader());

        try {
            message = bundle.getString(String.valueOf(errorNumber));
        } catch (java.util.MissingResourceException mre) {
            // Found bundle, but couldn't find exception translation.
            // Get the current language's NoExceptionTranslationForThisLocale
            // message.
            LOGGER.warn(
                    "Found bundle, but couldn't find exception translation. Get the current language's NoExceptionTranslationForThisLocale message.");
            bundle = ResourceBundle.getBundle("com.nividous.library.exception.i18n.NividousExceptionResource",
                    Locale.getDefault(), ExceptionMessageGenerator.class.getClassLoader());
            String noTranslationMessage = bundle.getString("NoExceptionTranslationForThisLocale");
            Object[] args = { SystemPropertiesPool.getLineSeparator() };
            return format(message, arguments) + format(noTranslationMessage, args);
        }
        return format(message, arguments);
    }

    /**
     * Get one of the generic headers used for the exception's toString().
     * 
     * E.g., "EXCEPTION DESCRIPTION: ", "ERROR CODE: ", etc.
     */
    public static String getHeader(String headerLabel) {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle("com.inclever.library.exception.i18n.ExceptionResource",
                    Locale.getDefault(), ExceptionMessageGenerator.class.getClassLoader());
            return bundle.getString(headerLabel);
        } catch (java.util.MissingResourceException mre) {
            return "[" + headerLabel + "]";
        }
    }

    /**
     * Return the formatted message for the given exception class and error
     * number.
     */

    protected static String format(String message, Object[] arguments) {
        // Catch any exception during formatting and try to throw that
        // exception. One possibility is toString() to an argument
        try {
            return MessageFormat.format(message, arguments);
        } catch (Exception ex) {
            ResourceBundle bundle = null;
            bundle = ResourceBundle.getBundle("com.nividous.library.exception.i18n.NividousExceptionResource",
                    Locale.getDefault(), ExceptionMessageGenerator.class.getClassLoader());
            String errorMessage = bundle.getString("ErrorFormattingMessage");

            Vector vec = new Vector();
            if (arguments != null) {
                for (int index = 0; index < arguments.length; index++) {
                    try {
                        vec.add(arguments[index].toString());
                    } catch (Exception ex2) {
                        vec.add(ex2);
                    }
                }
            }
            return MessageFormat.format(errorMessage, new Object[] { message, vec });
        }
    }

}
