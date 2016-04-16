package com.inclever.library.daoblend.core.dao.search;

import java.io.Serializable;
import java.util.List;

/**
 * This class is used to return the result of <code>searchAndCount()</code>
 * operations. It has just two properties: the result and the search and the
 * total (unpaged) count of the search.
 * 
 * 
 */
public class SearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    private List result;

    private int total = -1;

    /**
     * The result of the search.
     */
    @SuppressWarnings("rawtypes")
    public List getResult() {
        return result;
    }

    /**
     * The result of the search.
     */
    public void setResult(@SuppressWarnings("rawtypes") List results) {
        this.result = results;
    }

    /**
     * The total number of result that would have been returned if no maxResults
     * had been specified. (-1 means unspecified.)
     */
    public int getTotalCount() {
        return total;
    }

    /**
     * The total number of result that would have been returned if no maxResults
     * had been specified. (-1 means unspecified.)
     */
    public void setTotalCount(int totalCount) {
        this.total = totalCount;
    }
}
