package com.inclever.library.daoblend.exception;

import static com.inclever.library.common.LibraryErrorCodes.CORE_DAO;

;

/**
 * 
 * @author aashish.panchal@gmail.com
 *
 */
public interface DaoErrorCodes {

    int SQL_EXCEPTION = CORE_DAO + 0;

    int NULL_ENTITY_ERROR = CORE_DAO + 1;

    int ENTITY_SAVE_ERROR = CORE_DAO + 2;

    int SINGLE_ENTITY_FIND_ERROR = CORE_DAO + 3;

    int MULTI_ENTITY_FIND_ERROR = CORE_DAO + 4;

    int ENTITY_UPDATE_ERROR = CORE_DAO + 5;

    int ENTITY_DELETE_ERROR = CORE_DAO + 6;

    int UNSUPPORTED_FEATURE_ERROR = CORE_DAO + 7;

    int ENTITY_SEARCH_ERROR = CORE_DAO + 8;

    int ENTITY_REFRESH_ERROR = CORE_DAO + 9;

    int NATIVE_SQL_COL_PARAM_MISMATCH_ERROR = CORE_DAO + 10;

    int NATIVE_SQL_COL_PARAM_NUMBER_MISMATCH_ERROR = CORE_DAO + 11;

    int SEARCH_QUERY_PARAMETER_ERROR = CORE_DAO + 12;

    // Conversion Error Codes
    public final static int COULD_NOT_BE_CONVERTED_EXTENDED = CORE_DAO + 13;

    public final static int COULD_NOT_BE_CONVERTED = CORE_DAO + 14;

    public final static int INCORRECT_DATE_FORMAT = CORE_DAO + 15;

    public final static int COULD_NOT_CONVERT_TO_BYTE = CORE_DAO + 16;

    public final static int COULD_NOT_CONVERT_TO_BYTE_ARRAY = CORE_DAO + 17;

    public final static int COULD_NOT_BE_CONVERTED_TO_CLASS = CORE_DAO + 18;

    public final static int INCORRECT_TIME_FORMAT = CORE_DAO + 19;

    public final static int INCORRECT_TIMESTAMP_FORMAT = 4019;

}
