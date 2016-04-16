package com.inclever.library.sqlquerybuilder.dbobjects;

import com.inclever.library.sqlquerybuilder.util.Outputable;

public interface IColumn extends Outputable {

    public enum ColumnType {
        BIT("BIT"), REAL("REAL"), CHAR("CHAR"), TEXT("TEXT"), DATE("DATE"), TIME("TIME"), FLOAT("FLOAT"), BIGINT(
                "BIGINT"), DOUBLE("DOUBLE"), STRING("STRING"), BINARY("BINARY"), NUMERIC("NUMERIC"), DECIMAL(
                        "DECIMAL"), BOOLEAN("BOOLEAN"), TINYINT("TINYINT"), INTEGER("INTEGER"), VARCHAR(
                                "VARCHAR"), SMALLINT("SMALLINT"), VARBINARY("VARBINARY"), TIMESTAMP(
                                        "TIMESTAMP"), LONGVARCHAR("LONGVARCHAR"), VARCHAR2("VARCHAR2");

        private final String type;

        ColumnType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    Table getTable();

    String getName();
}
