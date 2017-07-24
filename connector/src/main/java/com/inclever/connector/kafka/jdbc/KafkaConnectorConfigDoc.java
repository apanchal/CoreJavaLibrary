package com.inclever.connector.kafka.jdbc;

/**
 * 
 * @author apanchal
 *
 */
public enum KafkaConnectorConfigDoc {

    CONNECTION_URL_DOC("JDBC connection URL for the database to load."), 
    POLL_INTERVAL_MS_DOC("Frequency in ms to poll for new data in each table."), 
    BATCH_MAX_ROWS_DOC("Maximum number of rows to include in a single batch when polling for new data. "
                            + "This setting can be used to limit the amount of data buffered internally in the connector."),
    TABLE_POLL_INTERVAL_MS_DOC("Frequency in ms to poll for new or removed tables, which may result in updated task "
                                + "configurations to start polling for data in added tables or stop polling for data in "
                                + "removed tables."),
    TABLE_BLACKLIST_DOC("List of tables to exclude from copying. If specified, table.whitelist may not be set."), 
    TABLE_WHITELIST_DOC("List of tables to include in copying. If specified, table.blacklist may not be set."), 
    INCREMENTING_COLUMN_NAME_DOC("The name of the strictly incrementing column to use to detect new rows. Any empty value "
                                        + "indicates the column should be autodetected by looking for an auto-incrementing column. "
                                        + "This column may not be nullable."), 
    TIMESTAMP_COLUMN_NAME_DOC("The name of the timestamp column to use to detect new or modified rows. This column may "
                                         + "not be nullable."), 
    TIMESTAMP_DELAY_INTERVAL_MS_DOC("How long to wait after a row with certain timestamp appears before we include it in the result. "
                                        + "You may choose to add some delay to allow transactions with earlier timestamp to complete. "
                                        + "The first execution will fetch all available records (i.e. starting at timestamp 0) until current time minus the delay. "
                                        + "Every following execution will get data from the last time we fetched until current time minus the delay."),
    QUERY_DOC("If specified, the query to perform to select new or updated rows. Use this setting if you "
                      + "want to join tables, select subsets of columns in a table, or filter data. If used, this"
                      + " connector will only copy data using this query -- whole-table copying will be disabled."
                      + " Different query modes may still be used for incremental updates, but in order to "
                      + "properly construct the incremental query, it must be possible to append a WHERE clause "
                      + "to this query (i.e. no WHERE clauses may be used). If you use a WHERE clause, it must "
                      + "handle incremental queries itself."), 
    TOPIC_PREFIX_DOC("Prefix to prepend to table names to generate the name of the Kafka topic to publish data "
                            + "to, or in the case of a custom query, the full name of the topic to publish to."), 
    TABLE_TYPE_DOC("By default, the JDBC connector will only detect tables with type TABLE from the source Database. "
                            + "This config allows a command separated list of table types to extract. Options include:\n"
                            + "* TABLE\n"
                            + "* VIEW\n"
                            + "* SYSTEM TABLE\n"
                            + "* GLOBAL TEMPORARY\n"
                            + "* LOCAL TEMPORARY\n"
                            + "* ALIAS\n"
                            + "* SYNONYM\n"
                            + "In most cases it only makes sense to have either TABLE or VIEW."), 
    MODE_DOC( "The mode for updating a table each time it is polled. Options include:\n"
                  + "  * bulk - perform a bulk load of the entire table each time it is polled\n"
                  + "  * incrementing - use a strictly incrementing column on each table to "
                  + "detect only new rows. Note that this will not detect modifications or "
                  + "deletions of existing rows.\n"
                  + "  * timestamp - use a timestamp (or timestamp-like) column to detect new and modified "
                  + "rows. This assumes the column is updated with each write, and that values are "
                  + "monotonically incrementing, but not necessarily unique.\n"
                  + "  * timestamp+incrementing - use two columns, a timestamp column that detects new and "
                  + "modified rows and a strictly incrementing column which provides a globally unique ID for "
                  + "updates so each row can be assigned a unique stream offset.");

    private String doc;

    KafkaConnectorConfigDoc(String doc) {
        this.doc = doc;
    }

    public String getDoc() {
        return doc;
    }
}
