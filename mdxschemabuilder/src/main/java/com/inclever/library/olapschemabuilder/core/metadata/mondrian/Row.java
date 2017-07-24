package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

/**
 * Row definition for an inline table. Must have one Column for each ColumnDef
 * in the InlineTable.
 */
public class Row {
    private List<Value> values; // optional array

    public Row() {
	values = new ArrayList<Value>();
    }

    public void addValue(Value value) {
	values.add(value);
    }

    public List<Value> getValues() {
	return values;
    }

}
