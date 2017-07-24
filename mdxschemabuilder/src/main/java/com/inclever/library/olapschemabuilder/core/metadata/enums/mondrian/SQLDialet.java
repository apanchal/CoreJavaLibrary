package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

public enum SQLDialet {
    GENERIC("generic"), ACCESS("access"), DB2("db2"), DERBY("derby"), FIREBIRD(
	    "firebird"), HSQLDB("hsqldb"), MSSQL("mssql"), MYSQL("mysql"), ORACLE(
	    "oracle"), POSTGRES("postgres"), SYBASE("sybase"), TERADATA(
	    "teradata"), INGRES("ingres"), LUCIDDB("luciddb");

    private String dialect;

    SQLDialet(String dialect) {
	this.dialect = dialect;
    }

    public String getDialect() {
	return dialect;
    }

}