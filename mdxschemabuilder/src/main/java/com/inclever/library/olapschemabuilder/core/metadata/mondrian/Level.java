package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.LevelDataType;
import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.LevelHideMember;
import com.inclever.library.olapschemabuilder.core.metadata.enums.mondrian.LevelType;

public class Level {

    public String levelName; // optional attribute

    /**
     * The name of the table that the column comes from.
     */
    public String table; // optional attribute

    /**
     * The name of the column which holds the unique identifier of this level.
     */
    public String column; // optional attribute

    /**
     * The name of the column which holds the user identifier of this level.
     */
    public String nameColumn; // optional attribute

    /**
     * The name of the column which holds member ordinals. If this column is not
     * specified, the key column(Primary Key) is used for ordering.
     */
    public String ordinalColumn; // optional attribute

    /**
     * The name of the column which references the parent member in a
     * parent-child hierarchy.
     */
    public String parentColumn; // optional attribute

    /**
     * Value which identifies null parents in a parent-child hierarchy. Typical
     * values are 'NULL' and '0'.
     */
    public String nullParentValue; // optional attribute

    /**
     * The estimated number of members in this level. Setting this property
     * improves the performance
     */
    public String approxRowCount; // optional attribute

    /**
     * Indicates the type of this level's key column: String, Numeric, Integer,
     * Boolean, Date, Time or Timestamp. When generating SQL statements,
     * Mondrian encloses values for String columns in quotation marks, but
     * leaves values for Integer and Numeric columns un-quoted.
     * 
     * Date, Time, and Timestamp values are quoted according to the SQL dialect.
     * For a SQL-compliant dialect, the values appear prefixed by their
     * typename, for example, "DATE '2006-06-01'".
     * 
     * Note: Default value is String
     */
    public LevelDataType type = LevelDataType.STRING; // attribute

    /**
     * Whether members are unique across all parents. For example, zipcodes are
     * unique across all states. The first level's members are always unique.
     * Unique Member,
     * 
     * note: Default is false;
     */
    public Boolean uniqueMembers = Boolean.FALSE; // attribute default: false

    /**
     * Whether this is a regular or a time-related level. The value makes a
     * difference to time-related functions such as YTD (year-to-date).Note:
     * Default is Regular
     */
    public LevelType levelType = LevelType.REGULAR; // attribute
						    // default:Regular

    /**
     * Condition which determines whether a member of this level is hidden. If a
     * hierarchy has one or more levels with hidden members, then it is possible
     * that not all leaf members are the same distance from the root, and it is
     * termed a ragged hierarchy.
     * 
     * Allowable values are: Never (a member always appears; the default);
     * IfBlankName (a member doesn't appear if its name is null or empty); and
     * IfParentsName (a member appears unless its name matches the parent's.
     */
    public String hideMemberIf = LevelHideMember.NEVER.get_hideMemberIf(); // attribute
    // default:
    // Never

    /**
     * Name of a formatter class for the member labels being displayed. The
     * class must implement the mondrian.olap.MemberFormatter interface.
     */
    public String formatter; // optional attribute

    /**
     * A string being displayed instead of the level's name.
     */
    public String caption; // optional attribute

    /**
     * The name of the column which holds the caption for members.
     */
    public String captionColumn; // optional attribute

    /**
     * The SQL expression used to populate this level's key.
     */
    public KeyExpression keyExp; // optional element
    /**
     * The SQL expression used to populate this level's name. If not specified,
     * the level's key is used.
     */
    public NameExpression nameExp; // optional element
    /**
     * The SQL expression used to populate this level's ordinal.
     */
    public OrdinalExpression ordinalExp; // optional element
    /**
     * The SQL expression used to join to the parent member in a parent-child
     * hierarchy.
     */
    public ParentExpression parentExp; // optional element

    public Closure closure; // optional element

    public List<Property> properties; // optional array

    public Level() {
	super();
	properties = new ArrayList<Property>();
    }

    /**
     * Creates Level with Name
     * 
     * @param levelName
     */
    public Level(String levelName) {
	this();
	this.levelName = levelName;
    }

    /**
     * 
     * @return
     */
    public String getLevelName() {
	return levelName;
    }

    public void setLevelName(String levelName) {
	this.levelName = levelName;
    }

    public String getTable() {
	return table;
    }

    public void setTable(String table) {
	this.table = table;
    }

    public String getColumn() {
	return column;
    }

    public void setColumn(String column) {
	this.column = column;
    }

    public String getNameColumn() {
	return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
	this.nameColumn = nameColumn;
    }

    public String getOrdinalColumn() {
	return ordinalColumn;
    }

    public void setOrdinalColumn(String ordinalColumn) {
	this.ordinalColumn = ordinalColumn;
    }

    public String getParentColumn() {
	return parentColumn;
    }

    public void setParentColumn(String parentColumn) {
	this.parentColumn = parentColumn;
    }

    public String getNullParentValue() {
	return nullParentValue;
    }

    public void setNullParentValue(String nullParentValue) {
	this.nullParentValue = nullParentValue;
    }

    public String getApproxRowCount() {
	return approxRowCount;
    }

    public void setApproxRowCount(String approxRowCount) {
	this.approxRowCount = approxRowCount;
    }

    public LevelDataType getType() {
	return type;
    }

    public void setType(LevelDataType type) {
	this.type = type;
    }

    public Boolean getUniqueMembers() {
	return uniqueMembers;
    }

    public void setUniqueMembers(Boolean uniqueMembers) {
	this.uniqueMembers = uniqueMembers;
    }

    public LevelType getLevelType() {
	return levelType;
    }

    public void setLevelType(LevelType levelType) {
	this.levelType = levelType;
    }

    public String getHideMemberIf() {
	return hideMemberIf;
    }

    public void setHideMemberIf(String hideMemberIf) {
	this.hideMemberIf = hideMemberIf;
    }

    public String getFormatter() {
	return formatter;
    }

    public void setFormatter(String formatter) {
	this.formatter = formatter;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public String getCaptionColumn() {
	return captionColumn;
    }

    public void setCaptionColumn(String captionColumn) {
	this.captionColumn = captionColumn;
    }

    public Closure getClosure() {
	return closure;
    }

    public void setClosure(Closure closure) {
	this.closure = closure;
    }

    /*
     * public List<Property> getProperties() { return properties; }
     */

    public void addProperty(Property property) {
	properties.add(property);
    }

    public void setKeyExp(KeyExpression keyExp) {
	this.keyExp = keyExp;
    }

    public void setNameExp(NameExpression nameExp) {
	this.nameExp = nameExp;
    }

    public void setOrdinalExp(OrdinalExpression ordinalExp) {
	this.ordinalExp = ordinalExp;
    }

    public void setParentExp(ParentExpression parentExp) {
	this.parentExp = parentExp;
    }

    public String getName() {
	return "Level";
    }

    public Expression getKeyExp() {
	if (keyExp != null) {
	    return keyExp;
	} else if (column != null) {
	    return new Column(table, column);
	} else {
	    return null;
	}
    }

    public Expression getNameExp() {
	if (nameExp != null) {
	    return nameExp;
	} else if (nameColumn != null) {
	    return new Column(table, nameColumn);
	} else {
	    return null;
	}
    }

    public Expression getCaptionExp() {
	if (captionColumn != null) {
	    return new Column(table, captionColumn);
	} else {
	    return null;
	}
    }

    public Expression getOrdinalExp() {
	if (ordinalExp != null) {
	    return ordinalExp;
	} else if (ordinalColumn != null) {
	    return new Column(table, ordinalColumn);
	} else {
	    return null;
	}
    }

    public Expression getParentExp() {
	if (parentExp != null) {
	    return parentExp;
	} else if (parentColumn != null) {
	    return new Column(table, parentColumn);
	} else {
	    return null;
	}
    }

    public Expression getPropertyExp(int i) {
	return new Column(table, properties.get(i).getColumn());
    }

    // TODO:
    // FIXME:
    /*
     * public SqlQuery.Datatype getDatatype() { return
     * SqlQuery.Datatype.valueOf(type); }
     */
    // END pass-through code block ---
}