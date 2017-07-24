package com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian;

public enum LevelType {
    REGULAR("Regular"), TIME_YEARS("TimeYears"), TIME_QUARTERS("TimeQuarters"), TIME_MONTHS(
	    "TimeMonths"), TIME_WEEKS("TimeWeeks"), TIME_DAYS("TimeDays");

    String levelType;

    private LevelType(String levelType) {
	this.levelType = levelType;
    }

    public String getLevelType() {
	return levelType;
    }
}
