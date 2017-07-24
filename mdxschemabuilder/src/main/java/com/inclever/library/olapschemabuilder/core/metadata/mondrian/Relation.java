package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * A table, inline table or view
 */
public abstract class Relation extends RelationOrJoin {
    public Relation() {
	super();
    }

    public abstract String getAlias();

}
