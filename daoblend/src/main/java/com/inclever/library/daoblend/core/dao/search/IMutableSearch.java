package com.inclever.library.daoblend.core.dao.search;

import java.util.List;

/**
 * <code>IMutableSearch</code> is an extension of <code>ISearch</code> that
 * provides setters for all of the properties.
 * 
 * @author Ashish Panchal (apanchal@inclever.com)
 * 
 */
public interface IMutableSearch extends ISearch {

    IMutableSearch setFirstResult(int firstResult);

    IMutableSearch setMaxResults(int maxResults);

    IMutableSearch setPage(int page);

    IMutableSearch setSearchClass(Class<?> searchClass);

    IMutableSearch setFilters(List<Filter> filters);

    IMutableSearch setDisjunction(boolean disjunction);

    IMutableSearch setSorts(List<Sort> sorts);

    IMutableSearch setFields(List<Field> fields);

    IMutableSearch setDistinct(boolean distinct);

    IMutableSearch setFetches(List<String> fetches);

    IMutableSearch setResultMode(int resultMode);

    IMutableSearch setConstructorExpressionClass(
	    String constructorExpressionObjectName);
}
