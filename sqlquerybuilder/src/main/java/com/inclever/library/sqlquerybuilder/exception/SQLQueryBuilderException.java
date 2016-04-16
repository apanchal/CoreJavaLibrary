package com.inclever.library.sqlquerybuilder.exception;

import com.inclever.library.common.SystemPropertiesPool;
import com.inclever.library.configuration.LibraryVersion;
import com.inclever.library.exception.LibraryRuntimeException;
import com.inclever.library.exception.i18n.ExceptionMessageGenerator;
import com.inclever.library.sqlquerybuilder.exception.i18n.SQLQueryBuilderExceptionResource;

public class SQLQueryBuilderException extends LibraryRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String moduelName = "(SQL Query Builder Services - ".concat(LibraryVersion.getVersion())
            .concat(".v").concat(LibraryVersion.getBuildDate()).concat("-").concat(LibraryVersion.getBuildNumber())
            .concat(")");

    public static final int EMPTY_SELECT_ERROR = 5000;

    public SQLQueryBuilderException() {
        super();
    }

    /**
     * 
     */
    protected SQLQueryBuilderException(String message) {
        super(message);
    }

    public static SQLQueryBuilderException emptySelect() {

        Object[] args = { SystemPropertiesPool.getLineSeparator() };

        SQLQueryBuilderException sqlQueryBuilderException = new SQLQueryBuilderException(ExceptionMessageGenerator
                .buildMessage(SQLQueryBuilderExceptionResource.class.getName(), EMPTY_SELECT_ERROR, args));
        sqlQueryBuilderException.setErrorCode(EMPTY_SELECT_ERROR);
        return sqlQueryBuilderException;
    }

    @Override
    public String getModuleName() {
        return moduelName;
    }
}
