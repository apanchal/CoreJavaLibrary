package com.inclever.library.daoblend.core.dao.query;

public enum QueryHints {

    /**
     * Query timeout in seconds ( e.g. new Integer(10) )
     */
    HIBERNATE_TIME_OUT("org.hibernate.timeout"),
    /**
     * Number of rows fetched by the JDBC driver per roundtrip ( e.g. new
     * Integer(50) )
     */
    HIBERNATE_FETCH_SIZE("org.hibernate.fetchSize"),
    /**
     * Add a comment to the SQL query, useful for the DBA ( e.g. new String(
     * "fetch all orders in 1 statement") )
     */
    HIBERNATE_COMMENT("org.hibernate.comment"),
    /**
     * Whether or not a query is cacheable ( e.g. new Boolean(true) ), defaults
     * to false
     */
    HIBERNATE_CACHEABLE("org.hibernate.cacheable"),
    /**
     * Override the cache mode for this query ( e.g. CacheMode.REFRESH OR
     * "REFRESH")
     */
    HIBERNATE_CACHE_MODE("org.hibernate.cacheMode"),
    /**
     * Cache region of this query ( e.g. new String("regionName") )
     */
    HIBERNATE_CACHE_REGION("org.hibernate.cacheRegion"),
    /**
     * Entities retrieved by this query will be loaded in a read-only mode where
     * Hibernate will never dirty-check them or make changes persistent ( e.g.
     * new Boolean(true) ), default to false
     */
    HIBERNATE_READ_ONLY("org.hibernate.readOnly"),
    /**
     * Flush mode used for this query (useful to pass Hibernate specific flush
     * modes, in particular MANUAL).
     */
    HIBERNATE_FLUSH_MODE("org.hibernate.flushMode");

    private String hintName;

    QueryHints(String hintName) {
        this.hintName = hintName;
    }

    public String getHintName() {
        return hintName;
    }
}
