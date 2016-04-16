/**
 * 
 */
package com.inclever.library.daoblend.exception;

import com.inclever.library.common.StringPool;
import com.inclever.library.configuration.LibraryVersion;
import com.inclever.library.daoblend.exception.i18n.DAOBlendAccessExceptionResource;
import com.inclever.library.exception.LibraryRuntimeException;
import com.inclever.library.exception.i18n.ExceptionMessageGenerator;

import static com.inclever.library.daoblend.exception.DaoErrorCodes.NULL_ENTITY_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_SAVE_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_UPDATE_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_DELETE_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_REFRESH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_SEARCH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.SEARCH_QUERY_PARAMETER_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.NATIVE_SQL_COL_PARAM_MISMATCH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.NATIVE_SQL_COL_PARAM_NUMBER_MISMATCH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.SINGLE_ENTITY_FIND_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.MULTI_ENTITY_FIND_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.UNSUPPORTED_FEATURE_ERROR;

/**
 * <P>
 * <B>Purpose</B>: Wrapper for any database exception that occurred through
 * DAOBlend.
 * 
 * @author Ashish Panchal(aashish.panchal@gmail.com)
 * 
 */
public class DaoRuntimeException extends LibraryRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 8411406475901669783L;

    private static final String MODULE_NAME = StringPool.OPEN_PARENTHESIS.concat("DAOBlend Persistence Services - "
            .concat(LibraryVersion.getVersion()).concat(".v").concat(LibraryVersion.getBuildDate())
            .concat(StringPool.DASH).concat(LibraryVersion.getBuildNumber()).concat(StringPool.CLOSE_PARENTHESIS));

    public DaoRuntimeException() {
        super();
    }

    public DaoRuntimeException(Throwable throwable) {
        super(throwable);
    }

    /**
     * 
     */
    protected DaoRuntimeException(String message) {
        super(message);
    }

    /**
     * 
     */
    protected DaoRuntimeException(String message, Exception exception) {
        super(message, exception);

    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public static DaoRuntimeException cannotPerformCRUDOnNullEntity() {
        Object[] args = {};

        DaoRuntimeException validationException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), NULL_ENTITY_ERROR, args));
        validationException.setErrorCode(NULL_ENTITY_ERROR);
        return validationException;
    }

    public static DaoRuntimeException cannotSaveEntity(String entityClazzName, String internalMessage,
            Throwable throwable) {

        Object[] args = { entityClazzName, internalMessage };

        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), ENTITY_SAVE_ERROR, args));
        daoBlendAccessException.setErrorCode(ENTITY_SAVE_ERROR);
        daoBlendAccessException.setInternalException(throwable);
        return daoBlendAccessException;
    }

    public static DaoRuntimeException cannotFindEntity(String entityClazzName, String internalMessage,
            Throwable throwable) {

        Object[] args = { entityClazzName, internalMessage };

        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), SINGLE_ENTITY_FIND_ERROR, args));
        daoBlendAccessException.setErrorCode(SINGLE_ENTITY_FIND_ERROR);
        if (throwable != null) {
            daoBlendAccessException.setInternalException(throwable);
        }
        return daoBlendAccessException;
    }

    public static DaoRuntimeException cannotFindEntities(String entityClazzName, Throwable throwable) {

        Object[] args = { entityClazzName };

        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), MULTI_ENTITY_FIND_ERROR, args));
        daoBlendAccessException.setErrorCode(MULTI_ENTITY_FIND_ERROR);
        if (throwable != null) {
            daoBlendAccessException.setInternalException(throwable);
        }
        return daoBlendAccessException;
    }

    public static DaoRuntimeException cannotUpdateEntity(String entityClazzName, String persistID,
            String internalMessage, Throwable throwable) {

        Object[] args = { entityClazzName, persistID, internalMessage };

        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), ENTITY_UPDATE_ERROR, args));
        daoBlendAccessException.setErrorCode(ENTITY_UPDATE_ERROR);
        if (throwable != null) {
            daoBlendAccessException.setInternalException(throwable);
        }
        return daoBlendAccessException;
    }

    public static DaoRuntimeException cannotDeleteEntity(String entityClazzName, String persistID,
            String internalMessage, Throwable throwable) {

        Object[] args = { entityClazzName, persistID, internalMessage };

        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), ENTITY_DELETE_ERROR, args));
        daoBlendAccessException.setErrorCode(ENTITY_DELETE_ERROR);
        if (throwable != null) {
            daoBlendAccessException.setInternalException(throwable);
        }
        return daoBlendAccessException;
    }

    public static DaoRuntimeException entityRefreshException(String entityClazzName, String persistID,
            String internalMessage, Throwable throwable) {

        Object[] args = { entityClazzName, persistID, internalMessage };

        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), ENTITY_REFRESH_ERROR, args));
        daoBlendAccessException.setErrorCode(ENTITY_REFRESH_ERROR);
        if (throwable != null) {
            daoBlendAccessException.setInternalException(throwable);
        }
        return daoBlendAccessException;
    }

    public static DaoRuntimeException searchError(String message, Throwable throwable) {
        Object[] args = { message };
        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), ENTITY_SEARCH_ERROR, args));
        daoBlendAccessException.setErrorCode(ENTITY_SEARCH_ERROR);
        if (throwable != null) {
            daoBlendAccessException.setInternalException(throwable);
        }
        return daoBlendAccessException;
    }

    public static DaoRuntimeException searchParameterMissingException(String query, Object parameters) {

        Object[] args = { query, parameters };

        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), SEARCH_QUERY_PARAMETER_ERROR, args));
        daoBlendAccessException.setErrorCode(SEARCH_QUERY_PARAMETER_ERROR);

        return daoBlendAccessException;
    }

    public static DaoRuntimeException UnSupportedFeatureError() {
        Object[] args = {};
        DaoRuntimeException daoBlendAccessException = new DaoRuntimeException(ExceptionMessageGenerator
                .buildMessage(DAOBlendAccessExceptionResource.class.getName(), UNSUPPORTED_FEATURE_ERROR, args));
        daoBlendAccessException.setErrorCode(UNSUPPORTED_FEATURE_ERROR);
        return daoBlendAccessException;
    }

    public static DaoRuntimeException ColumnParameterMismatchException(String beanClazzName, Throwable throwable) {

        Object[] args = { beanClazzName };

        DaoRuntimeException daoBlendRuntimeException = new DaoRuntimeException(ExceptionMessageGenerator.buildMessage(
                DAOBlendAccessExceptionResource.class.getName(), NATIVE_SQL_COL_PARAM_MISMATCH_ERROR, args));
        daoBlendRuntimeException.setErrorCode(NATIVE_SQL_COL_PARAM_MISMATCH_ERROR);
        daoBlendRuntimeException.setInternalException(throwable);
        return daoBlendRuntimeException;
    }

    public static DaoRuntimeException ColumnParameterNumberMismatchException(String beanClazzName) {
        Object[] args = { beanClazzName };

        DaoRuntimeException daoBlendRuntimeException = new DaoRuntimeException(ExceptionMessageGenerator.buildMessage(
                DAOBlendAccessExceptionResource.class.getName(), NATIVE_SQL_COL_PARAM_NUMBER_MISMATCH_ERROR, args));
        daoBlendRuntimeException.setErrorCode(NATIVE_SQL_COL_PARAM_NUMBER_MISMATCH_ERROR);
        return daoBlendRuntimeException;
    }

}
