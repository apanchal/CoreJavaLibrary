package com.inclever.library.sqlquerybuilder.criteria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.inclever.library.sqlquerybuilder.core.Matchable;
import com.inclever.library.sqlquerybuilder.dbobjects.Column;
import com.inclever.library.sqlquerybuilder.dbobjects.Table;
import com.inclever.library.sqlquerybuilder.literal.BooleanLiteral;
import com.inclever.library.sqlquerybuilder.literal.FloatLiteral;
import com.inclever.library.sqlquerybuilder.literal.IntegerLiteral;
import com.inclever.library.sqlquerybuilder.literal.StringLiteral;
import com.inclever.library.sqlquerybuilder.util.Output;

/**
 * 
 */
public class MatchCriteria implements Criteria {

    private final Matchable left;

    private final Matchable right;

    private final MatchCriteriaEnum matchCriteriaEnum;

    // TODO: Add
    public MatchCriteria(Matchable left, MatchCriteriaEnum matchCriteriaEnum, Matchable right) {
        this.left = left;
        this.matchCriteriaEnum = matchCriteriaEnum;
        this.right = right;
    }

    public MatchCriteria(Column column, MatchCriteriaEnum matchType, boolean value) {
        this(column, matchType, new BooleanLiteral(value));
    }

    /**
     * Initializes a MatchCriteria with a given column, comparison operator, and
     * date operand that the criteria will use to make a comparison between the
     * given column and the date.
     *
     * @param column
     *            the column to use in the date comparison.
     * @param operator
     *            the comparison operator to use in the date comparison.
     * @param operand
     *            the date literal to use in the comparison.
     */
    public MatchCriteria(Column column, MatchCriteriaEnum operator, Date operand) {
        this(column, operator, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(operand));
    }

    public MatchCriteria(Column column, MatchCriteriaEnum matchType, double value) {
        this(column, matchType, new FloatLiteral(value));
    }

    public MatchCriteria(Column column, MatchCriteriaEnum matchType, long value) {
        this(column, matchType, new IntegerLiteral(value));
    }

    public MatchCriteria(Column column, MatchCriteriaEnum matchType, String value) {
        this(column, matchType, new StringLiteral(value));
    }

    public MatchCriteria(Table table, String columnname, MatchCriteriaEnum matchType, boolean value) {
        this(new Column(table, columnname), matchType, value);
    }

    /**
     * Initializes a MatchCriteria with a table, column name is this table,
     * comparison operator, and date operand that the criteria will use to make
     * a comparison between the given table column and the date.
     *
     * @param table
     *            the table that contains a column having the given name to use
     *            in the date comparison.
     * @param columnName
     *            the name of the column to use in the date comparison.
     * @param operator
     *            the comparison operator to use in the date comparison.
     * @param operand
     *            the date literal to use in the comparison.
     */
    public MatchCriteria(Table table, String columnName, MatchCriteriaEnum operator, Date operand) {
        this(new Column(table, columnName), operator, operand);
    }

    public MatchCriteria(Table table, String columnname, MatchCriteriaEnum matchType, double value) {
        this(new Column(table, columnname), matchType, value);
    }

    public MatchCriteria(Table table, String columnname, MatchCriteriaEnum matchType, long value) {
        this(new Column(table, columnname), matchType, value);
    }

    public MatchCriteria(Table table, String columnname, MatchCriteriaEnum matchType, String value) {
        this(new Column(table, columnname), matchType, value);
    }

    public Matchable getLeft() {
        return left;
    }

    public String getComparisonOperator() {
        return matchCriteriaEnum.getOperator();
    }

    public Matchable getRight() {
        return right;
    }

    @Override
    public void write(Output out) {
        left.write(out);
        out.print(' ').print(matchCriteriaEnum.getOperator()).print(' ');
        right.write(out);
    }

    @Override
    public void addReferencedTablesTo(Set<Table> tables) {
        left.addReferencedTablesTo(tables);
        right.addReferencedTablesTo(tables);
    }
}
