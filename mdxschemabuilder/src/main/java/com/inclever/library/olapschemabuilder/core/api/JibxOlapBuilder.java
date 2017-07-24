package com.inclever.library.olapschemabuilder.core.api;


import java.io.IOException;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.slf4j.Logger;

import com.inclever.library.logging.LogManagerFactory;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Schema;
import com.inclever.library.olapschemabuilder.exception.OlapSchemaBuilderException;

public class JibxOlapBuilder extends BaseOlapBuilder {

    Logger logger = LogManagerFactory.getInstance().getLogger(
	    JibxOlapBuilder.class);

    public JibxOlapBuilder() {
	super();
    }

    @Override
    protected String doMarshalling() throws OlapSchemaBuilderException {
	logger.debug("Entered in doMarshalling");

	String schemaXML = null;

	StringWriter writer = null;

	try {
	    Schema obj = getSchema();
	    logger.debug("Schema Object " + obj);

	    IBindingFactory bfact = BindingDirectory.getFactory(Schema.class);
	    logger.debug("inialize Biding Factory:" + bfact);

	    // marshal object back out to string
	    IMarshallingContext mctx = bfact.createMarshallingContext();
	    logger.debug("inialized MarshallingContext:" + mctx);
	    mctx.setIndent(0);

	    writer = new StringWriter(512);

	    logger.debug("Starting marshlling");
	    mctx.marshalDocument(obj, "ISO-8859-1", false, writer);
	    logger.debug("Starting completed.");

	    schemaXML = writer.toString();
	    logger.debug("Generated XML \n" + schemaXML);

	} catch (Exception e) {
	    throw new OlapSchemaBuilderException(
		    "Failed to Create Olap Schama.", e);
	} finally {
	    if (writer != null) {
		try {
		    writer.close();
		} catch (IOException e) {
		    throw new OlapSchemaBuilderException(
			    "Severe Error Failed to Create Olap Schama.", e);
		}
	    }
	}
	return schemaXML;
    }

}
