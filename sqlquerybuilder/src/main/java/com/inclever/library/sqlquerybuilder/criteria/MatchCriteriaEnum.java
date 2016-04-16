package com.inclever.library.sqlquerybuilder.criteria;

public enum MatchCriteriaEnum {

    EQUALS("="), GREATER(">"), GREATEREQUAL(">="), LESS("<"), LESSEQUAL("<="), LIKE("LIKE"), NOTEQUAL("<>");

    private String operator;

    MatchCriteriaEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
