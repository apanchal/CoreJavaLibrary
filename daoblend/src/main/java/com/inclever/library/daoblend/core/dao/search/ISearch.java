package com.inclever.library.daoblend.core.dao.search;

import java.util.List;

/**
 * <code>ISearch</code> is intended to be an immutable interface and only
 * provides getters for each of the properties. The {@link IMutableSearch}
 * interface extends <code>ISearch</code> by adding setters for all the
 * properties.
 */
public interface ISearch {
    /**
     * Value for result mode. This is the default value. With
     * <code>RESULT_AUTO</code> the result mode is automatically determined
     * according to the following rules:
     * <ul>
     * <li>If any field is specified with a key, use <code>RESULT_MAP</code>.
     * <li>Otherwise, if zero or one fields are specified, use <code>
     * RESULT_SINGLE</code>.
     * <li>Otherwise, use <code>RESULT_ARRAY</code>.
     * </ul>
     * 
     * @see #getResultMode()
     */
    int RESULT_AUTO = 0;

    /**
     * Value for result mode. <code>RESULT_ARRAY</code> returns each result as
     * an Object array (<code>Object[]</code>) with the entries corresponding to
     * the fields added to the search. Here's an example:
     * 
     * <pre>
     * Search s = new Search(Person.class);
     * s.setResultMode(Search.RESULT_ARRAY);
     * s.addField(&quot;firstName&quot;);
     * s.addField(&quot;lastName&quot;);
     * for (Object[] array : dao.search(s)) {
     *     System.out.println(array[0] + &quot; &quot; + array[1]);
     * }
     * </pre>
     * 
     * @see #getResultMode()
     */
    int RESULT_ARRAY = 1;

    /**
     * Value for result mode. <code>RESULT_LIST</code> returns each result as a
     * list of Objects (<code>List&lt;Object&gt;</Code> ). Here's an example:
     * 
     * <pre>
     * Search s = new Search(Person.class);
     * s.setResultMode(Search.RESULT_LIST);
     * s.addField(&quot;firstName&quot;);
     * s.addField(&quot;lastName&quot;);
     * for (List&lt;Object&gt; list : dao.search(s)) {
     *     System.out.println(list.get(0) + &quot; &quot; + list.get(1));
     * }
     * </pre>
     * 
     * @see #getResultMode()
     */
    int RESULT_LIST = 2;

    /**
     * Value for result mode. <code>RESULT_MAP</code> returns each row as a map
     * with properties' names or keys for keys to the corresponding values.
     * Here's an example:
     * 
     * <pre>
     * Search s = new Search(Person.class);
     * s.setResultMode(Search.RESULT_MAP;
     * s.addField(&quot;firstName&quot;);
     * s.addField(&quot;lastName&quot;, &quot;ln&quot;);
     * for (Map&lt;String, Object&gt; map : dao.search(s)) {
     * 	System.out.println(map.get(&quot;firstName&quot;) + &quot; &quot; + map.get(&quot;ln&quot;));
     * }
     * </pre>
     * 
     * @see #getResultMode()
     */
    int RESULT_MAP = 3;

    /**
     * Value for result mode. <code>RESULT_SINGLE</code> - Exactly one field or
     * no fields must be specified to use this result mode. The result list
     * contains just the value of that property for each element. Here's an
     * example:
     * 
     * <pre>
     * Search s = new Search(Person.class);
     * s.setResultMode(Search.RESULT_SINGLE);
     * s.addField(&quot;firstName&quot;);
     * for (Object name : dao.search(s)) {
     *     System.out.println(name);
     * }
     * </pre>
     * 
     * @see #getResultMode()
     */
    int RESULT_SINGLE = 4;

    /**
     * Zero based index of first result record to return.
     * 
     * <p>
     * <code>&lt;= 0</code> for unspecified value.
     */
    int getFirstResult();

    /**
     * The maximum number of records to return. Also used as page size when
     * calculating the first record to return based on <code>page</code>.
     * 
     * <p>
     * <code>&lt;= 0</code> for unspecified value.
     */
    int getMaxResults();

    /**
     * Zero based index of the page of records to return. The size of a page is
     * determined by <code>maxResults</code>. If both <code>page</code> and
     * <code>maxResults</code> are specified (i.e. > 0), the first result
     * returned is calculated by <code>page * maxResults</code>.
     * 
     * <p>
     * <code>firstResult</code> has precedence over <code>page</code>. So if
     * <code>firstResult</code> is specified (i.e. > 0), <code>page</code> is
     * ignored.
     * 
     * <p>
     * <code>&lt;= 0</code> for unspecified value.
     */
    int getPage();

    Class<?> getSearchClass();

    List<Filter> getFilters();

    boolean isDisjunction();

    List<Sort> getSorts();

    List<Field> getFields();

    boolean isDistinct();

    List<String> getFetches();

    /**
     * Result mode tells the search what form to use for the result. Options
     * include <code>RESULT_AUTO</code>, <code>RESULT_ARRAY</code>, <code>
     * RESULT_LIST</code> , <code>RESULT_MAP</code> and <code>RESULT_SINGLE
     * </code>.
     * 
     * @see #RESULT_AUTO
     * @see #RESULT_ARRAY
     * @see #RESULT_LIST
     * @see #RESULT_MAP
     * @see #RESULT_SINGLE
     */
    int getResultMode();

    String getConstructorExpressionClass();

    boolean isConstructorExpressionQuery();

}
