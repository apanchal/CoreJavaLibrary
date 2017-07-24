/**
 *
 */
package com.inclever.library.olapschemabuilder.exception;

import com.inclever.library.common.SystemPropertiesPool;
import com.inclever.library.configuration.LibraryVersion;
import com.inclever.library.exception.LibraryException;
import com.inclever.library.exception.i18n.ExceptionMessageGenerator;
import com.inclever.library.olapschemabuilder.exception.i18n.OlapSchemaBuilderExceptionResource;

/**
 * @author Ashish
 * 
 */
public class OlapSchemaBuilderException extends LibraryException {

    private static final String moduelName = "(OLAP Schema Builder Services - ".concat(LibraryVersion.getVersion())
            .concat(".v").concat(LibraryVersion.getBuildDate()).concat("-").concat(LibraryVersion.getBuildNumber())
            .concat(")");

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int NULL_PARAMETER = 6000;

    public static final int NULL_OR_EMPTY_PARAMETER_LIST = 6001;

    public static final int NULL_SHARED_DIM = 6002;

    public static final int NULL_OR_EMPTY_SHARED_DIM_LIST = 6003;

    public static final int NULL_CUBE = 6004;

    public static final int NULL_OR_EMPTY_CUBE_LIST = 6005;

    public static final int NULL_FACT = 6006;

    public static final int ADD_FACT_ERROR = 6007;

    public static final int ADD_DIMENSION_ERROR = 6008;

    public static final int NULL_DIMENSION = 6009;

    public static final int ADD_MEASURE_ERROR = 6010;

    public static final int NULL_MEASURE = 6011;

    public static final int NULL_VIRTUAL_CUBE = 6012;

    public static final int NULL_NAMED_SET = 6013;

    public static final int NULL_UDF = 6014;

    public static final int NULL_ROLE = 6015;

    /**
     *
     */
    public OlapSchemaBuilderException() {
        super();
    }

    /**
     * @param message
     */
    public OlapSchemaBuilderException(String message) {
        super(message);

        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param throwable
     */
    public OlapSchemaBuilderException(String message, Throwable throwable) {
        super(message, throwable);
    }

    @Override
    public String getModuleName() {
        return moduelName;
    }

    /**
     * 
     * @param object
     * @param resourceKey
     * @return
     */
    public static OlapSchemaBuilderException cannotCreateSchemaObjectFromNullOrEmptyObject(int resourceKey) {
        Object[] args = { SystemPropertiesPool.getLineSeparator() };
        OlapSchemaBuilderException olapSchemaBuilderException = new OlapSchemaBuilderException(ExceptionMessageGenerator
                .buildMessage(OlapSchemaBuilderExceptionResource.class.getName(), resourceKey, args));
        olapSchemaBuilderException.setErrorCode(resourceKey);
        return olapSchemaBuilderException;
    }

    public static OlapSchemaBuilderException cubeElementAddError(String elementName, int resourceKey) {
        Object[] args = { elementName, SystemPropertiesPool.getLineSeparator() };
        OlapSchemaBuilderException olapSchemaBuilderException = new OlapSchemaBuilderException(ExceptionMessageGenerator
                .buildMessage(OlapSchemaBuilderExceptionResource.class.getName(), resourceKey, args));
        olapSchemaBuilderException.setErrorCode(resourceKey);
        return olapSchemaBuilderException;
    }

}
