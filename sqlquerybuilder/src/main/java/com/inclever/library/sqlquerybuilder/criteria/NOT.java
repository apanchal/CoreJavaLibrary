package com.inclever.library.sqlquerybuilder.criteria;

/**
 * Class NOT is a {@link BaseLogicGroup} extension that generates the SQL syntax
 * for the negation operator in a condition in an SQL Where clause.
 * 
 */
public class NOT extends BaseLogicGroup {
    /**
     * Initializes an SQL NOT operator with the given criteria that appears to
     * the right of the operator.
     * 
     * @param right
     *            the criteria or operand to which the NOT operator applies.
     */
    public NOT(Criteria right) {
        super("NOT", new NoCriteria(), right);
    }
}
