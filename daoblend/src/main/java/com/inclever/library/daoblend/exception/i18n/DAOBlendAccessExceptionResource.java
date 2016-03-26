package com.inclever.library.daoblend.exception.i18n;

import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_DELETE_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_REFRESH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_SAVE_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_SEARCH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.SEARCH_QUERY_PARAMETER_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.ENTITY_UPDATE_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.MULTI_ENTITY_FIND_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.NULL_ENTITY_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.SINGLE_ENTITY_FIND_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.UNSUPPORTED_FEATURE_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.NATIVE_SQL_COL_PARAM_MISMATCH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.NATIVE_SQL_COL_PARAM_NUMBER_MISMATCH_ERROR;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_BE_CONVERTED_EXTENDED;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_BE_CONVERTED;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.INCORRECT_DATE_FORMAT;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_CONVERT_TO_BYTE_ARRAY;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.COULD_NOT_BE_CONVERTED_TO_CLASS;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.INCORRECT_TIME_FORMAT;
import static com.inclever.library.daoblend.exception.DaoErrorCodes.INCORRECT_TIMESTAMP_FORMAT;

import java.util.ListResourceBundle;

/**
 * English ResourceBundle for DatabaseException messages.
 * 
 * @author apanchal
 * @since 2.0.0
 */
public class DAOBlendAccessExceptionResource extends ListResourceBundle {

    /**
     * Lookup table.
     */
    private static final Object[][] contents = {
	    { String.valueOf(NULL_ENTITY_ERROR),
		    "Cannot execute any CRUD operation on null entity object." },
	    { String.valueOf(ENTITY_SAVE_ERROR),
		    "Cannot persist Entity class [{0}], due to an internal error {1}." },
	    { String.valueOf(SINGLE_ENTITY_FIND_ERROR),
		    "Cannot find Entity class [{0}] with persistent id # {1}." },
	    { String.valueOf(MULTI_ENTITY_FIND_ERROR),
		    "Cannot list entities for Entity class [{0}]" },
	    {
		    String.valueOf(ENTITY_UPDATE_ERROR),
		    "Cannot update the statue of Entity class [{0}] with  persistent id # {1}, due to an internal error {2} ." },
	    {
		    String.valueOf(ENTITY_DELETE_ERROR),
		    "Cannot delete the Entity class [{0}] with  persistent id # {1}, due to an internal error {2} ." },
	    {
		    String.valueOf(UNSUPPORTED_FEATURE_ERROR),
		    "Unimplemented Feature, try in higher version then the current DAOBleand version." },
	    { String.valueOf(ENTITY_SEARCH_ERROR),
		    "Failed to Search as passed query string is  either null or empty[{0}]." },
	    {
		    String.valueOf(SEARCH_QUERY_PARAMETER_ERROR),
		    "Query [{0}] is parameterzied, but not found any parameters ({1}) to get replaced." },
	    {
		    String.valueOf(ENTITY_REFRESH_ERROR),
		    "Cannot refrsh the state of Entity class [{0}] with  persistent id # {1}, due to an internal error {2}." },
	    {
		    String.valueOf(NATIVE_SQL_COL_PARAM_MISMATCH_ERROR),
		    "Exception while processing query results-ensure column order matches {0} constructor parameters order." },
	    {
		    String.valueOf(NATIVE_SQL_COL_PARAM_NUMBER_MISMATCH_ERROR),
		    "Number of selected columns does not match number of constructor arguments for: {0}" },
	    {
		    String.valueOf(COULD_NOT_BE_CONVERTED_EXTENDED),
		    "The object [{0}], of class [{1}], from mapping [{2}] with descriptor [{3}], could not be converted to [{4}]." },
	    { String.valueOf(COULD_NOT_BE_CONVERTED),
		    "The object [{0}], of class [{1}], could not be converted to [{2}]." },
	    { String.valueOf(INCORRECT_DATE_FORMAT),
		    "Incorrect date format: [{0}] (expected [YYYY-MM-DD])" },
	    { String.valueOf(COULD_NOT_CONVERT_TO_BYTE_ARRAY),
		    "[{0}] must be of even length to be converted to a byte array." },
	    {
		    String.valueOf(COULD_NOT_BE_CONVERTED_TO_CLASS),
		    "The object [{0}], of class [{1}], could not be converted to [{2}].  Ensure that the class [{2}] is on the CLASSPATH.  "
			    + "You may need to use alternate API passing in the appropriate class loader as required, "
			    + "or setting it on the default DaoConversionManager" },
	    { String.valueOf(INCORRECT_TIME_FORMAT),
		    "Incorrect time format: [{0}] (expected [HH:MM:SS])" },
	    { String.valueOf(INCORRECT_TIMESTAMP_FORMAT),
		    "Incorrect timestamp format: [{0}] (expected [YYYY-MM-DD HH:MM:SS.NNNNNNNNN])" } };

    /**
     * Return the lookup table.
     */
    @Override
    protected Object[][] getContents() {
	return contents;
    }

}
