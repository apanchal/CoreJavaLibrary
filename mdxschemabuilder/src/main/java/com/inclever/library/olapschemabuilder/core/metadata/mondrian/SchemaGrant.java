package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.GrantAccessValue;

/**
 * Grants (or denies) this role access to this schema. access may be "all",
 * "all_dimensions", or "none". If access is "all_dimensions", the role has
 * access to all dimensions but still needs explicit access to cubes. See
 * mondrian.olap.Role#grant(mondrian.olap.Schema,int).
 */
public class SchemaGrant extends Grant {
    private List<CubeGrant> cubeGrants; // optional array

    public SchemaGrant() {
	super();
	cubeGrants = new ArrayList<CubeGrant>();
    }

    public SchemaGrant(GrantAccessValue accessValue) {
	super(accessValue);
	cubeGrants = new ArrayList<CubeGrant>();
    }

    public void addCubeGrants(CubeGrant cubeGrant) {
	if (cubeGrant != null) {
	    cubeGrants.add(cubeGrant);
	}
    }

}
