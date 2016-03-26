package com.inclever.library.daoblend.core.dao.query;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.LockModeType;

import com.google.common.base.MoreObjects;

public class NamedQuery {

	private String queryName;

	private Map<String, Object> queryParamMap;

	private boolean isNamedParam = true;

	private LockModeType lockModeType;

	private Map<String, Object> hints;

	private int maxResults;

	private int startPosition;

	/**
	 * 
	 */
	public NamedQuery() {
		super();
		maxResults = Integer.MAX_VALUE;
		this.hints = new HashMap<String, Object>();
	}

	/**
	 * 
	 * @param queryName
	 */
	public NamedQuery(String queryName) {
		this();
		this.queryName = queryName;
	}

	/**
	 * 
	 * @param queryName
	 * @param queryParamMap
	 */
	public NamedQuery(String queryName, Map<String, Object> queryParamMap) {
		this(queryName);
		this.queryParamMap = queryParamMap;
	}

	/**
	 * 
	 * @param queryName
	 * @param queryParamMap
	 * @param isNamedParam
	 */
	public NamedQuery(String queryName, Map<String, Object> queryParamMap, boolean isNamedParam) {
		this(queryName, queryParamMap);
		this.isNamedParam = isNamedParam;
	}

	/**
	 * 
	 * @return
	 */
	public String getQueryName() {
		return queryName;
	}

	/**
	 * 
	 * @param queryName
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	/**
	 * 
	 * @return Map<<String, Object>, query paramater map
	 */
	public Map<String, Object> getQueryParamMap() {
		return queryParamMap;
	}

	/**
	 * 
	 * @param queryParamMap
	 *            - Map<String,Object> named parameter to be re-placed in named
	 *            query.
	 */
	public void setQueryParamMap(Map<String, Object> queryParamMap) {
		this.queryParamMap = queryParamMap;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNamedParam() {
		return isNamedParam;
	}

	/**
	 * 
	 * @param isNamedParam
	 *            - boolean, default is true(named parameter like
	 *            :employeeName)), but it must be false in case query has number
	 *            parameter (e.g., ?1)
	 */
	public void setNamedParam(boolean isNamedParam) {
		this.isNamedParam = isNamedParam;
	}

	public LockModeType getLockModeType() {
		return lockModeType;
	}

	public void setLockModeType(LockModeType lockModeType) {
		this.lockModeType = lockModeType;
	}

	/**
	 * The position of the first result the query object was set to retrieve.
	 * Returns 0 if setFirstResult was not applied to the query object.
	 * 
	 * @return position of the first result
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * 
	 * @param startPosition
	 *            - position of the first result, numbered from 0
	 */
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	/**
	 * The maximum number of results the query object was set to retrieve.
	 * Returns Integer.MAX_VALUE if setMaxResults was not set.
	 * 
	 * @return
	 */
	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public Map<String, Object> getHints() {
		return hints;
	}

	/**
	 * 
	 * @param paramName
	 * @param value
	 */
	public void addQueryParameter(String paramName, Object value) {
		if (getQueryParamMap() == null) {
			queryParamMap = new HashMap<String, Object>();
		}
		queryParamMap.put(paramName, value);
	}

	public void addHints(QueryHints queryHint, Object value) {
		if (getHints() == null) {
			hints = new HashMap<String, Object>();
		}
		hints.put(queryHint.getHintName(), value);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("queryName", queryName).add("queryParam", queryParamMap)
				.add("startPosition", startPosition).add("maxResults", maxResults).add("lockModeType", lockModeType)
				.add("isNamedParam", isNamedParam).add("hints", hints).toString();
	}

}
