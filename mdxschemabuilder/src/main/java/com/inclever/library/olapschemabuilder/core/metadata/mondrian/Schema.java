package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * A schema is a collection of cubes and virtual cubes. It can also contain
 * shared dimensions (for use by those cubes), named sets, roles, and
 * declarations of user-defined functions.
 */
public final class Schema {
    /** Name of this schema */
    private String name; // required attribute

    /** Label for the measures dimension. */
    private String measuresCaption; // optional attribute

    /** The name of the default role for connections to this schema */
    private String defaultRole; // optional attribute

    private List<Parameter> parameters;

    /** Shared dimensions in this schema. */
    private List<Dimension> dimensions; // optional

    /** Cubes in this schema. */
    private List<Cube> cubes; // optional

    /** Virtual cubes in this schema. */
    private List<VirtualCube> virtualCubes; // optional array

    /** Named sets in this schema. */
    private List<NamedSet> namedSets; // optional array

    /** Roles in this schema. */
    private List<Role> roles; // optional array

    /** Declarations of user-defined functions in this schema. */
    private List<UserDefinedFunction> userDefinedFunctions; // optional array

    public Schema() {
	super();
	parameters = new ArrayList<Parameter>();
	dimensions = new ArrayList<Dimension>();
	cubes = new ArrayList<Cube>();
	virtualCubes = new ArrayList<VirtualCube>();
	namedSets = new ArrayList<NamedSet>();
	roles = new ArrayList<Role>();
	userDefinedFunctions = new ArrayList<UserDefinedFunction>();
    }

    public Schema(String name) {
	this();
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getMeasuresCaption() {
	return measuresCaption;
    }

    public void setMeasuresCaption(String measuresCaption) {
	this.measuresCaption = measuresCaption;
    }

    public String getDefaultRole() {
	return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
	this.defaultRole = defaultRole;
    }

    public void addParameter(Parameter parameter) {
	if (parameter != null) {
	    parameters.add(parameter);
	}
    }

    /**
     * Adds the dimension.
     * 
     * @param dimension
     *            the dimension
     */
    public void addDimension(Dimension dimension) {
	if (dimension != null) {
	    dimensions.add(dimension);
	}
    }

    public void addCube(Cube cube) {
	if (cube != null) {
	    cubes.add(cube);
	}
    }

    public void addVirtualCube(VirtualCube virtualCube) {
	if (virtualCube != null) {
	    virtualCubes.add(virtualCube);
	}
    }

    public void addNamedSet(NamedSet namedSet) {
	if (namedSet != null) {
	    namedSets.add(namedSet);
	}
    }

    public void addRole(Role role) {
	if (role != null) {
	    roles.add(role);
	}
    }

    public void addUserDefinedFunction(UserDefinedFunction userDefinedFunction) {
	if (userDefinedFunction != null) {
	    userDefinedFunctions.add(userDefinedFunction);
	}
    }

    public List<Dimension> getDimensions() {
	return dimensions;
    }

    public Cube getCube(String cubeName) {
	if (cubeName != null) {
	    for (Cube cube : cubes) {
		// TODO: Change direct variable access to getter method
		if (cubeName.equals(cube.getName())) {
		    return cube;
		}
	    }
	}
	return null;

	// TODO: FIX ME With Proper Exception
	// throw Util.newInternal("Cannot find cube '" + cubeName + "'");
	// throw new RuntimeException("Cannot find cube '" + cubeName + "'");
    }

    public Dimension getPublicDimension(String dimensionName) {
	if (dimensionName != null) {
	    for (Dimension dimension : dimensions) {
		if (dimension.getName().equals(dimensionName)) {
		    return dimension;
		}
	    }
	}

	// TODO: FIX ME With Proper Exception
	// throw Util.newInternal("Cannot find public dimension '" +
	// dimensionName + "'");
	throw new RuntimeException("Cannot find public dimension '"
		+ dimensionName + "'");
    }
}
