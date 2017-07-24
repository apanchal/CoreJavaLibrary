package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

/**
 * A table or a join
 */
public abstract class RelationOrJoin {
    public RelationOrJoin() {
    }

    public abstract Relation find(String seekAlias);

    public boolean equals(Object o) {
	return this == o;
    }

    public int hashCode() {
	return System.identityHashCode(this);
    }

}
