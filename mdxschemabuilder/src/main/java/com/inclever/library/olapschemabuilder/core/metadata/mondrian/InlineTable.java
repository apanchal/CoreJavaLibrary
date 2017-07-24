package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

public class InlineTable extends Relation {
    private String alias; // optional attribute

    private ColumnDefs columnDefs; // required element

    private Rows rows; // required element

    public InlineTable() {
	super();
    }

    public InlineTable(ColumnDefs columnDefs, Rows rows) {
	this();
	this.columnDefs = columnDefs;
	this.rows = rows;
    }

    /** Convenience constructor. */
    public InlineTable(InlineTable inlineTable) {
	this.alias = inlineTable.alias;
	this.columnDefs = new ColumnDefs();
	// Get ColumnDef
	this.columnDefs.addColumnDef(inlineTable.columnDefs.getColumnDefList());

	this.rows = new Rows();
	this.rows.addRow(inlineTable.rows.getRowList());
    }

    public void setAlias(String alias) {
	this.alias = alias;
    }

    public String getAlias() {
	return alias;
    }

    public void setColumnDefs(ColumnDefs columnDefs) {
	this.columnDefs = columnDefs;
    }

    public ColumnDefs getColumnDefs() {
	return columnDefs;
    }

    public void setRows(Rows rows) {
	this.rows = rows;
    }

    public Rows getRows() {
	return rows;
    }

    public String toString() {
	return "<inline data>";
    }

    public InlineTable find(String seekAlias) {
	return seekAlias.equals(this.alias) ? this : null;
    }

    public boolean equals(Object o) {
	if (o instanceof InlineTable) {
	    InlineTable that = (InlineTable) o;

	    return this.alias.equals(that.alias);
	} else {
	    return false;
	}
    }

    public int hashCode() {
	return toString().hashCode();
    }
}
