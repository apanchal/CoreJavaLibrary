package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * Holder for an array of ColumnDef elements
 */
public class ColumnDefs {
    private List<ColumnDef> columnDefList; // optional array

    public ColumnDefs() {
	this.columnDefList = new ArrayList<ColumnDef>();
    }

    public void addColumnDef(ColumnDef columnDef) {
	columnDefList.add(columnDef);
    }

    public void addColumnDef(List<ColumnDef> columnDefList) {
	columnDefList.addAll(columnDefList);
    }

    public List<ColumnDef> getColumnDefList() {
	return columnDefList;
    }

}
