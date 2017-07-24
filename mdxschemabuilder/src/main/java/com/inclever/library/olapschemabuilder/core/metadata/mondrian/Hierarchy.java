package com.inclever.library.olapschemabuilder.core.metadata.mondrian;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

/**
 * Defines a hierarchy. You must specify at most one <Relation> or
 * memberReaderClass. If you specify none, the hierarchy is assumed to come from
 * the same fact table of the current cube.
 */
public class Hierarchy {
    private Boolean hasAll; // required attribute

    private String name; // optional attribute

    private String allMemberName; // optional attribute

    private String allMemberCaption; // optional attribute

    private String allLevelName; // optional attribute

    private String primaryKey; // optional attribute

    private String primaryKeyTable; // optional attribute

    private String defaultMember; // optional attribute

    private String memberReaderClass; // optional attribute

    private String caption; // optional attribute

    /**
     * The {@link TableVO.Table table}, {@link JoinVO.Join set of tables},
     * {@link ViewVO.View SQL statement}, or {@link InlineTableVO.InlineTable
     * inline table} which populates this hierarchy.
     */

    private RelationOrJoin relation; // optional element

    /**
     *
     */
    private List<Level> levels; // optional array

    private List<MemberReaderParameter> memberReaderParameters; // optional

    // array

    /**
     * Create Hierarchy
     */
    public Hierarchy() {
	super();
	hasAll = Boolean.TRUE;
	levels = new ArrayList<Level>();
	memberReaderParameters = new ArrayList<MemberReaderParameter>();
    }

    /**
     * Create Hierarchy with name and has all attribute.
     * 
     * @param hierarchyName
     * @param hasAll
     */
    public Hierarchy(String name, Boolean hasAll) {
	this();
	this.name = name;
	this.hasAll = hasAll;
    }

    public Hierarchy(Boolean hasAll) {
	this(null, hasAll);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Boolean getHasAll() {
	return hasAll;
    }

    public void setHasAll(Boolean hasAll) {
	this.hasAll = hasAll;
    }

    public String getAllMemberName() {
	return allMemberName;
    }

    public void setAllMemberName(String allMemberName) {
	this.allMemberName = allMemberName;
    }

    public String getAllMemberCaption() {
	return allMemberCaption;
    }

    public void setAllMemberCaption(String allMemberCaption) {
	this.allMemberCaption = allMemberCaption;
    }

    public String getAllLevelName() {
	return allLevelName;
    }

    public void setAllLevelName(String allLevelName) {
	this.allLevelName = allLevelName;
    }

    public String getPrimaryKey() {
	return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
	this.primaryKey = primaryKey;
    }

    public String getPrimaryKeyTable() {
	return primaryKeyTable;
    }

    public void setPrimaryKeyTable(String primaryKeyTable) {
	this.primaryKeyTable = primaryKeyTable;
    }

    public String getDefaultMember() {
	return defaultMember;
    }

    public void setDefaultMember(String defaultMember) {
	this.defaultMember = defaultMember;
    }

    public String getMemberReaderClass() {
	return memberReaderClass;
    }

    public void setMemberReaderClass(String memberReaderClass) {
	this.memberReaderClass = memberReaderClass;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public RelationOrJoin getRelation() {
	return relation;
    }

    public void setRelation(RelationOrJoin relation) {
	this.relation = relation;
    }

    /**
     * Adds Level in to the Level List
     * 
     * @param level
     */
    public void addLevel(Level level) {
	if (level != null) {
	    levels.add(level);
	}
    }

    public void addMemberReaderParameter(
	    MemberReaderParameter memberReaderParameter) {
	if (memberReaderParameter != null) {
	    memberReaderParameters.add(memberReaderParameter);
	}
    }

    /*
     * implement equals required to handle cases where two different hierarchy
     * objects have equal contents. Resolves problem : of Hierarchy tree
     * disappears when expanded if there are two (or more) exactly similar
     * Hierarchy objects within a dimension. We can say two hierarchy objects
     * are different if their references are different.
     */
    public boolean equals(Object o) {
	return (this == o);
    }

    @Override
    public String toString() {
	return Objects.toStringHelper(this).add("name", getName())
		.add("hasAll", hasAll).add("allMemberName", getAllMemberName())
		.add("allMemberCaption", getAllMemberCaption())
		.add("allLevelName", getAllLevelName())
		.add("primaryKey", getPrimaryKey())
		.add("primaryKeyTable", getPrimaryKeyTable())
		.add("defaultMember", getDefaultMember())
		.add("memberReaderClass", getMemberReaderClass())
		.add("caption", getCaption()).add("relation", getRelation())
		.add("levels", levels)
		.add("memberReaderParameters", memberReaderParameters)
		.toString();
    }

}
