package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * Holder for an array of Row elements
 */
public class Rows {
    public List<Row> rowList; // optional array

    public Rows() {
	rowList = new ArrayList<Row>();
    }

    public void addRow(Row row) {
	rowList.add(row);
    }

    public void addRow(List<Row> rowList) {
	rowList.addAll(rowList);
    }

    public List<Row> getRowList() {
	return rowList;
    }
}
