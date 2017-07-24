package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

public class Join extends RelationOrJoin {
    private String leftAlias; // optional attribute

    private String leftKey; // required attribute

    private String rightAlias; // optional attribute

    private String rightKey; // required attribute

    private RelationOrJoin left; // required element

    private RelationOrJoin right; // required element

    public Join() {
	super();
    }

    public Join(String leftKey, RelationOrJoin left, String rightKey,
	    RelationOrJoin right) {
	this(null, leftKey, left, null, rightKey, right);
    }

    /** Convenience constructor. */
    public Join(String leftAlias, String leftKey, RelationOrJoin left,
	    String rightAlias, String rightKey, RelationOrJoin right) {
	this();
	this.leftAlias = leftAlias;
	this.leftKey = leftKey;
	this.left = left;
	this.rightAlias = rightAlias;
	this.rightKey = rightKey;
	this.right = right;
    }

    public String getLeftKey() {
	return leftKey;
    }

    public void setLeftKey(String leftKey) {
	this.leftKey = leftKey;
    }

    public String getRightKey() {
	return rightKey;
    }

    public void setRightKey(String rightKey) {
	this.rightKey = rightKey;
    }

    public RelationOrJoin getLeft() {
	return left;
    }

    public void setLeft(RelationOrJoin left) {
	this.left = left;
    }

    public RelationOrJoin getRight() {
	return right;
    }

    public void setRight(RelationOrJoin right) {
	this.right = right;
    }

    public void setLeftAlias(String leftAlias) {
	this.leftAlias = leftAlias;
    }

    public void setRightAlias(String rightAlias) {
	this.rightAlias = rightAlias;
    }

    /**
     * Returns the alias of the left join key, defaulting to left's alias if
     * left is a table.
     */
    public String getLeftAlias() {
	if (leftAlias != null) {
	    return leftAlias;
	}

	if (left instanceof Relation) {
	    return ((Relation) left).getAlias();
	}
	// TODO: FIX ME With Proper Exception
	// throw Util.newInternal("alias is required because " + left +
	// " is not a table");
	throw new RuntimeException("alias is required because " + left
		+ " is not a table");
    }

    /**
     * Returns the alias of the right join key, defaulting to right's alias if
     * right is a table.
     */
    public String getRightAlias() {
	if (rightAlias != null) {
	    return rightAlias;
	}

	if (right instanceof Relation) {
	    return ((Relation) right).getAlias();
	}

	if (right instanceof Join) {
	    return ((Join) right).getLeftAlias();
	}
	// TODO: FIX ME With Proper Exception
	// throw Util.newInternal("alias is required because " + right +
	// " is not a table");
	throw new RuntimeException("alias is required because " + right
		+ " is not a table");
    }

    public String toString() {
	return "(" + left + ") join (" + right + ") on " + leftAlias + "."
		+ leftKey + " = " + rightAlias + "." + rightKey;
    }

    public Relation find(String seekAlias) {
	Relation relation = left.find(seekAlias);

	if (relation == null) {
	    relation = right.find(seekAlias);
	}

	return relation;
    }

    public boolean equals(Object o) {
	return (this == o);
    }

    // END pass-through code block ---
}
