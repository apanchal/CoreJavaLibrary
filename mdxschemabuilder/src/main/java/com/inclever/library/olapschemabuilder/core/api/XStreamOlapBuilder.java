/**
 * 
 */
package com.inclever.library.olapschemabuilder.core.api;

import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Schema;
import com.inclever.library.olapschemabuilder.exception.OlapSchemaBuilderException;
import com.thoughtworks.xstream.XStream;

/**
 * @author apanchal
 * 
 */
public class XStreamOlapBuilder extends BaseOlapBuilder {

    /**
	 * 
	 */
    public XStreamOlapBuilder() {
	// TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.inclever.olapschemabuilder.core.api.BaseOlapBuilder#doMarshalling()
     */
    @Override
    protected String doMarshalling() throws OlapSchemaBuilderException {
	String schemaXML = null;

	try {
	    Schema object = getSchema();
	    XStream xstream = new XStream();
	    xstream.alias("Schema", Schema.class);
	    schemaXML = xstream.toXML(object);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return schemaXML;
    }

}
