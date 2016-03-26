package com.inclever.library.common;

public enum SupportedDatabase {

    ACCESS("access"), DB2("db2"), DERBY("derby"), FIREBIRD("firebird"), HSQLDB("hsqldb"), MSSQL("mssql"), MYSQL(
            "mysql"), ORACLE("oracle"), POSTGRES("postgres"), SYBASE("sybase"), TERADATA("teradata"), INGRES(
                    "INGRES"), INFOBRIGHT("INFOBRIGHT"), LUCIDDB("luciddb"), VERTICA("vertica");

    private String databasePlaformName;

    private SupportedDatabase(final String databasePlaformName) {
        this.databasePlaformName = databasePlaformName;
    }

    public String getDatabasePlaformName() {
        return databasePlaformName;
    }
}
