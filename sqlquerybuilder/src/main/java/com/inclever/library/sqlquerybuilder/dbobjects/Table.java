/**
 * 
 */
package com.inclever.library.sqlquerybuilder.dbobjects;

import com.inclever.library.common.StringPool;
import com.inclever.library.sqlquerybuilder.util.Output;
import com.inclever.library.sqlquerybuilder.util.Outputable;
import com.inclever.library.sqlquerybuilder.util.ToStringer;

/**
 * @author Ashish
 * 
 */
public class Table implements Outputable {

    private final String name;

    private final String alias;

    private final String schemaName;

    /**
     * 
     * @param schemaName
     * @param name
     * @param alias
     */
    public Table(String schemaName, String name, String alias) {
        this.schemaName = schemaName;
        this.name = name;
        this.alias = alias;
    }

    /**
     * 
     * @param name
     * @param alias
     */
    public Table(String name, String alias) {
        this(null, name, alias);
    }

    /**
     * 
     * @param name
     */
    public Table(String name) {
        this(null, name, null);
    }

    /**
     * 
     * @return
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * 
     * @return
     */
    public String getTableName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Get a column for a particular table.
     */
    public Column getColumn(String columnName) {
        return new Column(this, columnName);
    }

    public Column getWildcard() {
        return new Column(this, StringPool.STAR);
    }

    /**
     * 
     */
    @Override
    public void write(Output out) {
        // If Schema Name present then append table name with it.
        if (hasSchemaName()) {
            out.print(getSchemaName()).print(StringPool.DOT).print(getTableName());
        } else {
            out.print(getTableName());
        }
        // Append Alias Name
        if (hasAlias()) {
            out.print(StringPool.SPACE);
            out.print(getAlias());
        }
    }

    @Override
    public String toString() {
        return ToStringer.toString(this);
    }

    /**
     * Whether this table has an alias assigned.
     * 
     * @return boolean
     */
    public boolean hasAlias() {
        return alias != null;
    }

    /**
     * Whether this table has an Schema Name assigned.
     * 
     * @return
     */
    private boolean hasSchemaName() {
        return schemaName != null;
    }

}
