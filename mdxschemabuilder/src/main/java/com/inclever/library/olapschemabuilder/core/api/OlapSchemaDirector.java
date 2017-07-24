package com.inclever.library.olapschemabuilder.core.api;

import java.util.List;

import org.slf4j.Logger;

import com.inclever.library.exception.annotation.ExceptionHandler;
import com.inclever.library.exception.core.HandlerUtil;
import com.inclever.library.logging.LogManagerFactory;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Cube;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.CubeDimension;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Dimension;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Measure;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.NamedSet;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Parameter;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Relation;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Role;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.UserDefinedFunction;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.VirtualCube;
import com.inclever.library.olapschemabuilder.exception.OlapSchemaBuilderException;
import com.inclever.library.olapschemabuilder.exception.OlapSchemaBuilderExceptionHandler;

@ExceptionHandler(handler = OlapSchemaBuilderExceptionHandler.class)
public final class OlapSchemaDirector {

    Logger LOG = LogManagerFactory.getInstance().getLogger(
	    OlapSchemaDirector.class);

    BaseOlapBuilder baseOlapBuilder = null;

    public OlapSchemaDirector() {
	baseOlapBuilder = new JibxOlapBuilder();
	// baseOlapBuilder = new XStreamOlapBuilder();
    }

    public OlapSchemaDirector(BaseOlapBuilder baseOlapBuilder) {
	this.baseOlapBuilder = baseOlapBuilder;
    }

    /**
     * A Parameter defines a schema parameter. It can be referenced from an MDX
     * statement using the ParamRef function and, if not final, its value can be
     * overridden.
     * 
     * @param parameter
     *            - Parameter
     * @throws OlapSchemaBuilderException
     */
    public final void buildParameter(Parameter parameter)
	    throws OlapSchemaBuilderException {
	try {
	    baseOlapBuilder.buildParameter(parameter);
	} catch (OlapSchemaBuilderException schemaBuilderException) {
	    HandlerUtil.handle(schemaBuilderException, new Object[] {});
	    throw schemaBuilderException;
	}

    }

    /**
     * A Parameter defines a schema parameter. It can be referenced from an MDX
     * statement using the ParamRef function and, if not final, its value can be
     * overridden.
     * 
     * @param parameterList
     * @throws OlapSchemaBuilderException
     */
    public final void buildParameters(List<Parameter> parameterList)
	    throws OlapSchemaBuilderException {
	if (parameterList != null && !parameterList.isEmpty()) {
	    for (Parameter parameter : parameterList) {
		baseOlapBuilder.buildParameter(parameter);
	    }
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_OR_EMPTY_PARAMETER_LIST);
	}
    }

    /**
     * Create a SharedDimension in Schema.
     * 
     * @param dimension
     * @throws OlapSchemaBuilderException
     */
    public final void buildSharedDimension(Dimension dimension)
	    throws OlapSchemaBuilderException {
	baseOlapBuilder.buildSharedDimension(dimension);
    }

    /**
     * 
     * @param dimensionList
     * @throws OlapSchemaBuilderException
     */
    public final void buildSharedDimensions(List<Dimension> dimensionList)
	    throws OlapSchemaBuilderException {
	if (dimensionList != null && !dimensionList.isEmpty()) {
	    for (Dimension dimension : dimensionList) {
		buildSharedDimension(dimension);
	    }
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_OR_EMPTY_SHARED_DIM_LIST);
	}
    }

    public final void buildCube(Cube cube) throws OlapSchemaBuilderException {
	baseOlapBuilder.buildCube(cube);
    }

    public final void buildCubes(List<Cube> cubeList)
	    throws OlapSchemaBuilderException {
	if (cubeList != null && !cubeList.isEmpty()) {
	    for (Cube cube : cubeList) {
		baseOlapBuilder.buildCube(cube);
	    }
	} else {
	    throw OlapSchemaBuilderException
		    .cannotCreateSchemaObjectFromNullOrEmptyObject(OlapSchemaBuilderException.NULL_OR_EMPTY_CUBE_LIST);

	}
    }

    public final void buildCubeFact(String cubeName, Relation fact)
	    throws OlapSchemaBuilderException {
	baseOlapBuilder.buildCubeFact(cubeName, fact);
    }

    public final void buildCubeDimension(String cubeName,
	    CubeDimension cubeDimension) throws OlapSchemaBuilderException {
	baseOlapBuilder.buildCubeDimension(cubeName, cubeDimension);
    }

    public final void buildCubeDimensions(String cubeName,
	    List<CubeDimension> cubeDimensionList)
	    throws OlapSchemaBuilderException {
	if (cubeDimensionList != null && !cubeDimensionList.isEmpty()) {
	    for (CubeDimension cubeDimension : cubeDimensionList) {
		buildCubeDimension(cubeName, cubeDimension);
	    }
	} else {
	    // FIXME: USE RESOURCE BUNDLE
	    throw new OlapSchemaBuilderException(
		    "Can not build cube dimension from null or empty list");
	}
    }

    public final void buildCubeMeasure(String cubeName, Measure measure)
	    throws OlapSchemaBuilderException {
	baseOlapBuilder.buildCubeMeasure(cubeName, measure);
    }

    public final void buildCubeMeasures(String cubeName,
	    List<Measure> measureList) throws OlapSchemaBuilderException {
	if (measureList != null && !measureList.isEmpty()) {
	    for (Measure measure : measureList) {
		buildCubeMeasure(cubeName, measure);
	    }
	} else {
	    // FIXME: USE RESOURCE BUNDLE
	    throw new OlapSchemaBuilderException(
		    "Can not build cube dimension from null or empty list");
	}
    }

    public final void buildVirtualCube(VirtualCube virtualCube)
	    throws OlapSchemaBuilderException {
	baseOlapBuilder.buildVirtualCube(virtualCube);
    }

    public final void buildNamedSet(NamedSet namedSet)
	    throws OlapSchemaBuilderException {
	baseOlapBuilder.buildNamedSet(namedSet);
    }

    public final void buildUserDefinedFunction(UserDefinedFunction function)
	    throws OlapSchemaBuilderException {
	baseOlapBuilder.buildUserDefinedFunction(function);
    }

    public final void buildSchemaRole(Role role)
	    throws OlapSchemaBuilderException {
	baseOlapBuilder.buildSchemaRole(role);

    }

    public final String getSchemaXML() throws OlapSchemaBuilderException {
	return baseOlapBuilder.doMarshalling();
    }
}
