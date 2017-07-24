package com.inclever.library.olapschemabuilder.core.metadata.dialet.util.mondrian;

import java.util.Hashtable;
import java.util.Map;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.LevelDataType;
import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.LevelType;
import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.SQLDialet;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.KeyExpression;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.Level;
import com.inclever.library.olapschemabuilder.core.metadata.mondrian.SQL;

public class DateDimensionUtil {

    private final static Map<SQLDialet, String> yearMap = new Hashtable<SQLDialet, String>();
    private final static Map<SQLDialet, String> quarterMap = new Hashtable<SQLDialet, String>();
    private final static Map<SQLDialet, String> monthMap = new Hashtable<SQLDialet, String>();
    private final static Map<SQLDialet, String> weekMap = new Hashtable<SQLDialet, String>();
    private final static Map<SQLDialet, String> dayMap = new Hashtable<SQLDialet, String>();
    static {

	// Year
	yearMap.put(SQLDialet.ACCESS, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.DB2, "INTEGER(TO_CHAR(<COLUMN_NAME>, 'YYYY'))");
	yearMap.put(SQLDialet.DERBY, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.FIREBIRD, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.HSQLDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.MSSQL, "DATENAME(year,<COLUMN_NAME>)");
	yearMap.put(SQLDialet.MYSQL, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.ORACLE, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.POSTGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.SYBASE, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.TERADATA, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.INGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	yearMap.put(SQLDialet.LUCIDDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");

	// Quarter
	quarterMap.put(SQLDialet.ACCESS, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.DB2, "'Q' || CHAR(QUARTER(<COLUMN_NAME>))");
	quarterMap.put(SQLDialet.DERBY, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.FIREBIRD, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.HSQLDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap
		.put(SQLDialet.MSSQL, "'Q' + DATENAME(quarter,<COLUMN_NAME>)");
	quarterMap.put(SQLDialet.MYSQL, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.ORACLE, " 'Q' || TO_CHAR(<COLUMN_NAME>, 'Q')");
	quarterMap.put(SQLDialet.POSTGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.SYBASE, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.TERADATA, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.INGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	quarterMap.put(SQLDialet.LUCIDDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");

	// Month
	monthMap.put(SQLDialet.ACCESS, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.DB2, "monthname(<COLUMN_NAME>)");
	monthMap.put(SQLDialet.DERBY, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.FIREBIRD, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.HSQLDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.MSSQL, "DATENAME(month,<COLUMN_NAME>)");
	monthMap.put(SQLDialet.MYSQL, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.ORACLE, "TO_CHAR(<COLUMN_NAME>, 'Month')");
	monthMap.put(SQLDialet.POSTGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.SYBASE, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.TERADATA, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.INGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	monthMap.put(SQLDialet.LUCIDDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");

	// Week
	weekMap.put(SQLDialet.ACCESS, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.DB2, "WEEK(<COLUMN_NAME>)");
	weekMap.put(SQLDialet.DERBY, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.FIREBIRD, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.HSQLDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.MSSQL, "DATENAME(wk,<COLUMN_NAME>)");
	weekMap.put(SQLDialet.MYSQL, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.ORACLE, "TO_CHAR(<COLUMN_NAME>, 'WW')");
	weekMap.put(SQLDialet.POSTGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.SYBASE, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.TERADATA, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.INGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	weekMap.put(SQLDialet.LUCIDDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");

	// Day
	dayMap.put(SQLDialet.ACCESS, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.DB2, "substr(DAYNAME(<COLUMN_NAME>),1,3)");
	dayMap.put(SQLDialet.DERBY, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.FIREBIRD, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.HSQLDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.MSSQL, "DATENAME(dw,<COLUMN_NAME>)");
	dayMap.put(SQLDialet.MYSQL, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.ORACLE, "TO_CHAR(<COLUMN_NAME>, 'Dy')");
	dayMap.put(SQLDialet.POSTGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.SYBASE, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.TERADATA, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.INGRES, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");
	dayMap.put(SQLDialet.LUCIDDB, "TO_CHAR(<COLUMN_NAME>, 'YYYY')");

    }

    /**
     * 
     * @param database
     * @return @
     */
    public Level createYearLevelMember(String levelName,
	    LevelDataType dataType, String columnName) {

	Level yearLevel = new Level(levelName);
	yearLevel.setLevelType(LevelType.TIME_YEARS);
	yearLevel.setType(dataType);
	yearLevel.setUniqueMembers(Boolean.TRUE);

	KeyExpression yearExpression = new KeyExpression();

	for (SQLDialet dialet : SQLDialet.values()) {
	    if (dialet.equals(SQLDialet.GENERIC)) {
		continue;
	    }
	    SQL sql = new SQL(dialet);
	    sql.setCdata(yearMap.get(dialet).replaceAll("<COLUMN_NAME>",
		    columnName));
	    yearExpression.addExpression(sql);

	}

	yearLevel.setKeyExp(yearExpression);

	return yearLevel;

    }

    public Level createQuarterLevelMember(String levelName,
	    LevelDataType dataType, String columnName) {

	Level quarterLevel = new Level(levelName);
	quarterLevel.setLevelType(LevelType.TIME_QUARTERS);
	quarterLevel.setType(dataType);
	quarterLevel.setUniqueMembers(Boolean.FALSE);

	KeyExpression quarterExpression = new KeyExpression();

	for (SQLDialet dialet : SQLDialet.values()) {
	    if (dialet.equals(SQLDialet.GENERIC)) {
		continue;
	    }
	    SQL sql = new SQL(dialet);
	    sql.setCdata(quarterMap.get(dialet).replaceAll("<COLUMN_NAME>",
		    columnName));
	    quarterExpression.addExpression(sql);
	}

	quarterLevel.setKeyExp(quarterExpression);

	return quarterLevel;

    }

    public Level createMonthLevelMember(String levelName,
	    LevelDataType dataType, String columnName) {

	Level monthLevel = new Level(levelName);
	monthLevel.setLevelType(LevelType.TIME_MONTHS);
	monthLevel.setType(dataType);
	monthLevel.setUniqueMembers(Boolean.FALSE);

	KeyExpression monthExpression = new KeyExpression();

	for (SQLDialet dialet : SQLDialet.values()) {
	    if (dialet.equals(SQLDialet.GENERIC)) {
		continue;
	    }
	    SQL sql = new SQL(dialet);
	    sql.setCdata(monthMap.get(dialet).replaceAll("<COLUMN_NAME>",
		    columnName));
	    monthExpression.addExpression(sql);
	}

	monthLevel.setKeyExp(monthExpression);

	return monthLevel;

    }

    public Level createWeekLevelMember(String levelName,
	    LevelDataType dataType, String columnName) {

	Level weekLevel = new Level(levelName);
	weekLevel.setLevelType(LevelType.TIME_WEEKS);
	weekLevel.setType(dataType);
	weekLevel.setUniqueMembers(Boolean.TRUE);

	KeyExpression weekExpression = new KeyExpression();

	for (SQLDialet dialet : SQLDialet.values()) {
	    if (dialet.equals(SQLDialet.GENERIC)) {
		continue;
	    }
	    SQL sql = new SQL(dialet);
	    sql.setCdata(weekMap.get(dialet).replaceAll("<COLUMN_NAME>",
		    columnName));
	    weekExpression.addExpression(sql);
	}

	weekLevel.setKeyExp(weekExpression);

	return weekLevel;

    }

    public Level createDayLevelMember(String levelName, LevelDataType dataType,
	    String columnName) {

	Level dayLevel = new Level(levelName);
	dayLevel.setLevelType(LevelType.TIME_DAYS);
	dayLevel.setType(dataType);
	dayLevel.setUniqueMembers(Boolean.TRUE);

	KeyExpression dayExpression = new KeyExpression();

	for (SQLDialet dialet : SQLDialet.values()) {
	    if (dialet.equals(SQLDialet.GENERIC)) {
		continue;
	    }
	    SQL sql = new SQL(dialet);
	    sql.setCdata(dayMap.get(dialet).replaceAll("<COLUMN_NAME>",
		    columnName));
	    dayExpression.addExpression(sql);
	}

	dayLevel.setKeyExp(dayExpression);

	return dayLevel;

    }

}
