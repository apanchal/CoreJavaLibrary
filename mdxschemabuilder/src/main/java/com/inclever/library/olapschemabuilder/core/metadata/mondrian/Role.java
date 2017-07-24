package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * A role defines an access-control profile. It has a series of grants (or
 * denials) for schema elements.
 */
public class Role {

    private String name; // required attribute

    public List<SchemaGrant> schemaGrants; // optional array

    /**
     * Instantiates a new role.
     * 
     * @param roleName
     *            the role name
     */

    public Role() {
	schemaGrants = new ArrayList<SchemaGrant>();
    }

    public Role(String name) {
	this();
	this.name = name;

    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void addSchemaGrant(SchemaGrant schemaGrant) {
	if (schemaGrant != null) {
	    schemaGrants.add(schemaGrant);
	}
    }

}
