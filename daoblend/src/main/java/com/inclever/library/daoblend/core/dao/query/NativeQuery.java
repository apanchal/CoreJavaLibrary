package com.inclever.library.daoblend.core.dao.query;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

public class NativeQuery {

    private String queryString;

    private List<Object> queryParamList;

    private boolean printPretty = false;

    public NativeQuery() {
        super();
    }

    public NativeQuery(@NotNull String queryString) {
        this();
        this.queryString = queryString;
    }

    public NativeQuery(String queryString, List<Object> queryParamList) {
        this(queryString);
        this.queryParamList = queryParamList;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryName) {
        this.queryString = queryName;
    }

    public List<Object> getQueryParamList() {
        return queryParamList;
    }

    public void setQueryParamList(List<Object> queryParamList) {
        this.queryParamList = queryParamList;
    }

    public boolean isPrintPretty() {
        return printPretty;
    }

    public void setPrintPretty(boolean printPretty) {
        this.printPretty = printPretty;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Query String", getQueryString())
                .add("Query Parameters", getQueryParamList()).toString();

    }

}
