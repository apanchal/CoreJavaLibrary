/**
 * 
 */
package com.inclever.library.daoblend.exception;

import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_BE_CONVERTED;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_BE_CONVERTED_EXTENDED;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_BE_CONVERTED_TO_CLASS;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_CONVERT_TO_BYTE_ARRAY;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.INCORRECT_DATE_FORMAT;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.INCORRECT_TIMESTAMP_FORMAT;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.INCORRECT_TIME_FORMAT;

import com.inclever.library.daoblend.exception.i18n.DAOBlendAccessExceptionResource;
import com.inclever.library.exception.i18n.ExceptionMessageGenerator;

/**
 * @author aashish.panchal@gmail.com
 *
 */
@SuppressWarnings("rawtypes")
public class DaoConversionException extends DaoRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -6558179349571204320L;

    protected Class classToConvertTo;

    protected transient Object sourceObject;

    /**
     * 
     */
    public DaoConversionException() {
        super();
    }

    /**
     * @param message
     */
    public DaoConversionException(String message) {
        super(message);

    }

    /**
     * INTERNAL:
     */
    protected DaoConversionException(String message, Object sourceObject, Class classToConvertTo, Exception exception) {
        super(message, exception);
        setSourceObject(sourceObject);
        setClassToConvertTo(classToConvertTo);
    }

    /**
     * PUBLIC: Return the class to convert to.
     */
    public Class getClassToConvertTo() {
        return classToConvertTo;
    }

    /**
     * PUBLIC: Return the object for which the problem was detected.
     */
    public Object getSourceObject() {
        return sourceObject;
    }

    /**
     * INTERNAL: Set the class to convert to.
     */
    public void setClassToConvertTo(Class classToConvertTo) {
        this.classToConvertTo = classToConvertTo;
    }

    /**
     * INTERNAL: Set the object for which the problem was detected.
     */
    public void setSourceObject(Object sourceObject) {
        this.sourceObject = sourceObject;
    }

    // Couldn't find a way of simply changing the message on an existing
    // exception.
    // therefore, create a new exception with appropriate message and port
    // existing
    // info (stack trace and internal exception over)
    public static DaoConversionException couldNotBeConverted(Object mapping, Object descriptor,
            DaoConversionException exception) {
        Object sourceObject = exception.getSourceObject();
        Class javaClass = exception.getClassToConvertTo();
        Exception original = (Exception) exception.getInternalException();

        Object[] args = { sourceObject, sourceObject.getClass(), mapping, descriptor, javaClass };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                COULD_NOT_BE_CONVERTED_EXTENDED, args);
        DaoConversionException conversionException = new DaoConversionException(message, sourceObject, javaClass,
                original);
        conversionException.setStackTrace(exception.getStackTrace());
        conversionException.setErrorCode(COULD_NOT_BE_CONVERTED_EXTENDED);
        return conversionException;
    }

    public static DaoConversionException couldNotBeConverted(Object object, Class javaClass) {
        Class objectClass = null;
        if (object != null) {
            objectClass = object.getClass();
        }
        Object[] args = { object, objectClass, javaClass };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                COULD_NOT_BE_CONVERTED, args);
        DaoConversionException conversionException = new DaoConversionException(message, object, javaClass, null);
        conversionException.setErrorCode(COULD_NOT_BE_CONVERTED);
        return conversionException;
    }

    public static DaoConversionException couldNotBeConverted(Object object, Class javaClass, Exception exception) {
        Class objectClass = null;
        if (object != null) {
            objectClass = object.getClass();
        }
        Object[] args = { object, objectClass, javaClass };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                COULD_NOT_BE_CONVERTED, args);
        DaoConversionException conversionException = new DaoConversionException(message, object, javaClass, exception);
        conversionException.setErrorCode(COULD_NOT_BE_CONVERTED);
        return conversionException;
    }

    public static DaoConversionException incorrectDateFormat(String dateString) {
        Object[] args = { dateString };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                INCORRECT_DATE_FORMAT, args);
        DaoConversionException conversionException = new DaoConversionException(message, dateString,
                java.sql.Date.class, null);
        conversionException.setErrorCode(INCORRECT_DATE_FORMAT);
        return conversionException;
    }

    public static DaoConversionException couldNotConvertToByteArray(Object object) {
        Object[] args = { object };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                COULD_NOT_CONVERT_TO_BYTE_ARRAY, args);
        DaoConversionException conversionException = new DaoConversionException(message, object, byte[].class, null);
        conversionException.setErrorCode(COULD_NOT_CONVERT_TO_BYTE_ARRAY);
        return conversionException;
    }

    public static DaoConversionException couldNotBeConvertedToClass(Object object, Class javaClass,
            Exception exception) {
        Object[] args = { object, object.getClass(), javaClass };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                COULD_NOT_BE_CONVERTED_TO_CLASS, args);
        DaoConversionException conversionException = new DaoConversionException(message, object, javaClass, exception);
        conversionException.setErrorCode(COULD_NOT_BE_CONVERTED_TO_CLASS);
        return conversionException;
    }

    public static DaoConversionException incorrectTimeFormat(String timeString) {
        Object[] args = { timeString };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                INCORRECT_TIME_FORMAT, args);
        DaoConversionException conversionException = new DaoConversionException(message, timeString,
                java.sql.Time.class, null);
        conversionException.setErrorCode(INCORRECT_TIME_FORMAT);
        return conversionException;
    }

    public static DaoConversionException incorrectTimestampFormat(String timestampString) {
        Object[] args = { timestampString };
        String message = ExceptionMessageGenerator.buildMessage(DAOBlendAccessExceptionResource.class.getName(),
                INCORRECT_TIMESTAMP_FORMAT, args);
        DaoConversionException conversionException = new DaoConversionException(message, timestampString,
                java.sql.Timestamp.class, null);
        conversionException.setErrorCode(INCORRECT_TIMESTAMP_FORMAT);
        return conversionException;
    }

}
