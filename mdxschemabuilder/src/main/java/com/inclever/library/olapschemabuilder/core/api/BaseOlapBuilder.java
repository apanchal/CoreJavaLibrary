package com.inclever.library.olapschemabuilder.core.api;

import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Cube;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.CubeDimension;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Dimension;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Measure;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.NamedSet;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Parameter;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Relation;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Role;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Schema;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.UserDefinedFunction;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.VirtualCube;
import com.inclever.library.olapschemabuilder.exception.OlapSchemaBuilderException;

public abstract class BaseOlapBuilder {

    private final Schema schema;

    protected BaseOlapBuilder() {
	schema = new Schema("BusinessInsight");
    }

    protected Schema getSchema() {
	return schema;
    }

    protected final void buildParameter(Parameter parameter)
	    throws OlapSchemaBuilderException {
	if (parameter != null) {
	    schema.addParameter(parameter);
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_PARAMETER);
	}
    }

    protected final void buildSharedDimension(Dimension dimension)
	    throws OlapSchemaBuilderException {
	if (dimension != null) {
	    schema.addDimension(dimension);
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_SHARED_DIM);
	}
    }

    protected final void buildCube(Cube cube) throws OlapSchemaBuilderException {
	if (cube != null) {
	    schema.addCube(cube);
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_CUBE);
	}
    }

    protected final void buildCubeFact(String cubeName, Relation fact)
	    throws OlapSchemaBuilderException {
	if (fact != null && !cubeName.isEmpty()) {
	    Cube cube = getSchema().getCube(cubeName);
	    if (cube != null) {
		cube.setFact(fact);
	    } else {
		throw OlapSchemaBuilderException.cubeElementAddError(cubeName,
			OlapSchemaBuilderException.ADD_FACT_ERROR);
	    }
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_FACT);
	}
    }

    protected final void buildCubeDimension(String cubeName,
	    CubeDimension cubeDimension) throws OlapSchemaBuilderException {
	if (cubeDimension != null && !cubeName.isEmpty()) {
	    Cube cube = getSchema().getCube(cubeName);
	    if (cube != null) {
		cube.addCubeDimensions(cubeDimension);
	    } else {
		throw OlapSchemaBuilderException.cubeElementAddError(cubeName,
			OlapSchemaBuilderException.ADD_DIMENSION_ERROR);
	    }
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_DIMENSION);
	}
    }

    protected final void buildCubeMeasure(String cubeName, Measure cubeMeasure)
	    throws OlapSchemaBuilderException {
	if (cubeMeasure != null && !cubeName.isEmpty()) {
	    Cube cube = getSchema().getCube(cubeName);
	    if (cube != null) {
		cube.addMeasures(cubeMeasure);
	    } else {
		throw OlapSchemaBuilderException.cubeElementAddError(cubeName,
			OlapSchemaBuilderException.ADD_MEASURE_ERROR);
	    }
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_MEASURE);
	}
    }

    protected final void buildVirtualCube(VirtualCube virtualCube)
	    throws OlapSchemaBuilderException {
	if (virtualCube != null) {
	    schema.addVirtualCube(virtualCube);
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_VIRTUAL_CUBE);
	}
    }

    protected final void buildNamedSet(NamedSet namedSet)
	    throws OlapSchemaBuilderException {
	if (namedSet != null) {
	    schema.addNamedSet(namedSet);
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_NAMED_SET);
	}
    }

    protected final void buildUserDefinedFunction(UserDefinedFunction function)
	    throws OlapSchemaBuilderException {
	if (function != null) {
	    schema.addUserDefinedFunction(function);
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_UDF);
	}
    }

    protected final void buildSchemaRole(Role role)
	    throws OlapSchemaBuilderException {
	if (role != null) {
	    schema.addRole(role);
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_ROLE);
	}
    }

    protected abstract String doMarshalling() throws OlapSchemaBuilderException;

}