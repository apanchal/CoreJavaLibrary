/**
 * 
 */
package com.inclever.library.olapschemabuilder.exception.i18n;

import java.util.ListResourceBundle;

import com.inclever.library.olapschemabuilder.exception.OlapSchemaBuilderException;

/**
 * @author apanchal
 * 
 */
public class OlapSchemaBuilderExceptionResource extends ListResourceBundle {

    /**
     * Lookup table.
     */
    static final Object[][] contents = {
	    { String.valueOf(OlapSchemaBuilderException.NULL_PARAMETER),
		    "Parameter cannot be null." },

	    {
		    String.valueOf(OlapSchemaBuilderException.NULL_OR_EMPTY_PARAMETER_LIST),
		    "Cannot build paramater from null or empty list." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_SHARED_DIM),
		    "Shared dimension cannot be null." },

	    {
		    String.valueOf(OlapSchemaBuilderException.NULL_OR_EMPTY_SHARED_DIM_LIST),
		    "Cannot build shared dimension from null or empty list." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_CUBE),
		    "Cube cannot be null." },

	    {
		    String.valueOf(OlapSchemaBuilderException.NULL_OR_EMPTY_CUBE_LIST),
		    "Cannot build cube from null or empty list." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_FACT),
		    "Cannot add null fact." },

	    {
		    String.valueOf(OlapSchemaBuilderException.ADD_FACT_ERROR),
		    "Cube [ {0} ] not found. Did you added Cube, need to add Cube before adding Fact into it." },

	    {
		    String.valueOf(OlapSchemaBuilderException.ADD_DIMENSION_ERROR),
		    "Cube [ {0} ] not found. Did you added Cube, need to add Cube before adding Dimension into it." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_DIMENSION),
		    "Can not add null dimension." },

	    {
		    String.valueOf(OlapSchemaBuilderException.ADD_MEASURE_ERROR),
		    "Cube [ {0} ] not found. Did you added Cube, need to add Cube before adding Measure into it." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_MEASURE),
		    "can not add null measure." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_VIRTUAL_CUBE),
		    "Virtual Cube can not be null." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_NAMED_SET),
		    "NamedSet can not be null." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_UDF),
		    "UserDefinedFunction can not be null." },

	    { String.valueOf(OlapSchemaBuilderException.NULL_ROLE),
		    "Role can not be null." } };

    /*
     * (non-Javadoc)
     * 
     * @see java.util.ListResourceBundle#getContents()
     */
    @Override
    protected Object[][] getContents() {
	return contents;
    }

}
