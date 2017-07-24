/**
 * 
 */
package com.inclever.connector.kafka.jdbc;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigException;

import com.inclever.connector.config.ConnectorConfig;

/**
 * @author apanchal
 *
 */
public class KafkaJdbcConnectorConfig extends AbstractConfig implements ConnectorConfig {

    /**
     * JDBC connection URL for the database to load.
     */
    public static final String CONNECTION_URL = "connection.url";

    /**
     * Frequency in ms to poll for new data in each table. <br>
     * Default: 5000
     * 
     * @see com.inclever.connector.config.kafka.KafkaJdbcConnectorConfig.
     *      Default. POLL_INTERVAL
     */
    public static final String POLL_INTERVAL_MS = "poll.interval.ms";

    /**
     * Maximum number of rows to include in a single batch when polling for new
     * data.<br>
     * Default: 100
     * 
     * @see com.inclever.connector.config.kafka.KafkaJdbcConnectorConfig.
     *      Default. BATCH_MAX_ROWS
     */
    public static final String BATCH_MAX_ROWS = "batch.max.rows";

    /**
     * The mode for updating a table each time it is polled. Options include:
     * <br>
     * <ul>
     * <li>bulk - perform a bulk load of the entire table each time it is polled
     * 
     * @see com.inclever.connector.kafka.KafkaJdbcConnectorConfig.Mode.BULK</li>
     *      <li>incrementing - use a strictly incrementing column on each table
     *      to detect only new rows. Note that this will not detect
     *      modifications or deletions of existing rows.
     * @see com.inclever.connector.kafka.KafkaJdbcConnectorConfig.Mode.
     *      INCREMENTING</li>
     *      <li>timestamp - use a timestamp (or timestamp-like) column to detect
     *      new and modified rows. This assumes the column is updated with each
     *      write, and that values are monotonically incrementing, but not
     *      necessarily unique.
     * @see com.inclever.connector.config.kafka.KafkaJdbcConnectorConfig.Mode.
     *      TIMESTAMP</li>
     *      <li>timestamp+incrementing - use two columns, a timestamp column
     *      that detects new and modified rows and a strictly incrementing
     *      column which provides a globally unique ID for updates so each row
     *      can be assigned a unique stream offset.
     * @see com.inclever.connector.kafka.KafkaJdbcConnectorConfig.Mode.
     *      TIMESTAMP_INCREMENTING</li>
     *      <ul>
     */
    public static final String MODE = "mode";

    /**
     * 
     * @author apanchal
     *
     */
    public enum Mode {

        UNSPECIFIED(""), BULK("bulk"), TIMESTAMP("timestamp"), INCREMENTING("incrementing"), TIMESTAMP_INCREMENTING(
                "timestamp+incrementing");

        private String mode;

        Mode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }
    }

    /**
     * 
     * @author apanchal
     *
     */
    public enum Default {

        POLL_INTERVAL(5000), BATCH_MAX_ROWS(100), TABLE_POLL_INTERVAL(60000), TIMESTAMP_DELAY_INTERVAL_MS(0);
        private int defaultValue;

        Default(int defaultValue) {
            this.defaultValue = defaultValue;
        }

        public int getDefaultValue() {
            return defaultValue;
        }
    }

    /**
     * The name of the strictly incrementing column to use to detect new rows.
     * Any empty value indicates the column should be autodetected by looking
     * for an auto-incrementing column. This column may not be nullable.
     */
    public static final String INCREMENTING_COLUMN_NAME = "incrementing.column.name";
    public static final String INCREMENTING_COLUMN_NAME_DEFAULT = "";

    /**
     * The name of the timestamp column to use to detect new or modified rows.
     * This column may not be nullable.
     */
    public static final String TIMESTAMP_COLUMN_NAME = "timestamp.column.name";
    public static final String TIMESTAMP_COLUMN_NAME_DEFAULT = "";

    /**
     * How long to wait after a row with certain timestamp appears before we
     * include it in the result.You may choose to add some delay to allow
     * transactions with earlier timestamp to complete.The first execution will
     * fetch all available records (i.e. starting at timestamp 0) until current
     * time minus the delay. " + "Every following execution will get data from
     * the last time we fetched until current time minus the delay.
     */
    public static final String TIMESTAMP_DELAY_INTERVAL_MS = "timestamp.delay.interval.ms";
    public static final long TIMESTAMP_DELAY_INTERVAL_MS_DEFAULT = 0;

    /**
     * Frequency in ms to poll for new or removed tables, which may result in
     * updated task configurations to start polling for data in added tables or
     * stop polling for data in removed tables. <br>
     * Default: 6000
     * 
     * @see com.inclever.connector.config.kafka.KafkaJdbcConnectorConfig.
     *      Default. TABLE_POLL_INTERVAL
     */
    public static final String TABLE_POLL_INTERVAL_MS = "table.poll.interval.ms";

    /**
     * List of tables to exclude from copying. If specified, table.whitelist may
     * not be set.can be specified as comma separated values
     */
    public static final String TABLE_BLACKLIST = "table.blacklist";
    private static final Object TABLE_BLACKLIST_DEFAULT = "";

    /**
     * List of tables to include in copying. If specified, table.blacklist may
     * not be set. can be specified as comma separated values
     */
    public static final String TABLE_WHITELIST = "table.whitelist";
    public static final String TABLE_WHITELIST_DEFAULT = "";

    /**
     * By default, the JDBC connector will only detect tables with type TABLE
     * from the source Database. " This config allows a command separated list
     * of table types to extract. Options include:<br>
     * TABLE<br>
     * VIEW<br>
     * SYSTEM TABLE<br>
     * GLOBAL TEMPORARY<br>
     * LOCAL TEMPORARY<br>
     * ALIAS<br>
     * SYNONYM<br>
     * In most cases it only makes sense to have either TABLE or VIEW.
     */
    public static final String TABLE_TYPE = "table.types";

    public static final String TABLE_TYPE_DEFAULT = "TABLE";

    /**
     * If specified, the query to perform to select new or updated rows. Use
     * this setting if you want to join tables, select subsets of columns in a
     * table, or filter data. If used, this connector will only copy data using
     * this query – whole-table copying will be disabled. Different query modes
     * may still be used for incremental updates, but in order to properly
     * construct the incremental query, it must be possible to append a WHERE
     * clause to this query (i.e. no WHERE clauses may be used). If you use a
     * WHERE clause, it must handle incremental queries itself.
     */
    public static final String QUERY = "query";
    public static final String QUERY_DEFAULT = "";

    /**
     * Prefix to prepend to table names to generate the name of the Kafka topic
     * to publish data to, or in the case of a custom query, the full name of
     * the topic to publish to.
     */
    public static final String TOPIC_PREFIX = "topic.prefix";

    static ConfigDef config = baseConfig();

    protected KafkaJdbcConnectorConfig(ConfigDef subclassConfigDef, Map<String, String> props) {
        super(subclassConfigDef, props);
    }

    /**
     * 
     * @param props
     */
    public KafkaJdbcConnectorConfig(Map<String, String> props) {
        super(config, props);
        String mode = getString(KafkaJdbcConnectorConfig.MODE);
        if (KafkaJdbcConnectorConfig.Mode.UNSPECIFIED.getMode().equals(mode))
            throw new ConfigException("Query mode must be specified");
    }

    public static ConfigDef baseConfig() {
        return new ConfigDef()
                .define(CONNECTION_URL, Type.STRING, Importance.HIGH,
                        KafkaConnectorConfigDoc.CONNECTION_URL_DOC.getDoc())
                .define(TABLE_WHITELIST, Type.LIST, TABLE_WHITELIST_DEFAULT, Importance.MEDIUM,
                        KafkaConnectorConfigDoc.TABLE_WHITELIST_DOC.getDoc())
                .define(TABLE_BLACKLIST, Type.LIST, TABLE_BLACKLIST_DEFAULT, Importance.MEDIUM,
                        KafkaConnectorConfigDoc.TABLE_BLACKLIST_DOC.getDoc())
                .define(TABLE_TYPE, Type.LIST, TABLE_TYPE_DEFAULT, Importance.LOW,
                        KafkaConnectorConfigDoc.TABLE_TYPE_DOC.getDoc())
                .define(MODE, Type.STRING, Mode.UNSPECIFIED.getMode(),
                        ConfigDef.ValidString.in(Mode.UNSPECIFIED.getMode(), Mode.BULK.getMode(),
                                Mode.TIMESTAMP.getMode(), Mode.INCREMENTING.getMode(),
                                Mode.TIMESTAMP_INCREMENTING.getMode()),
                        Importance.HIGH, KafkaConnectorConfigDoc.MODE_DOC.getDoc())
                .define(INCREMENTING_COLUMN_NAME, Type.STRING, INCREMENTING_COLUMN_NAME_DEFAULT, Importance.MEDIUM,
                        KafkaConnectorConfigDoc.INCREMENTING_COLUMN_NAME_DOC.getDoc())
                .define(TIMESTAMP_COLUMN_NAME, Type.STRING, TIMESTAMP_COLUMN_NAME_DEFAULT, Importance.MEDIUM,
                        KafkaConnectorConfigDoc.TIMESTAMP_COLUMN_NAME_DOC.getDoc())
                .define(QUERY, Type.STRING, QUERY_DEFAULT, Importance.MEDIUM,
                        KafkaConnectorConfigDoc.QUERY_DOC.getDoc())
                .define(POLL_INTERVAL_MS, Type.INT, Default.POLL_INTERVAL.getDefaultValue(), Importance.HIGH,
                        KafkaConnectorConfigDoc.POLL_INTERVAL_MS_DOC.getDoc())
                .define(BATCH_MAX_ROWS, Type.INT, Default.BATCH_MAX_ROWS.getDefaultValue(), Importance.LOW,
                        KafkaConnectorConfigDoc.BATCH_MAX_ROWS_DOC.getDoc())
                .define(TABLE_POLL_INTERVAL_MS, Type.LONG, Default.TABLE_POLL_INTERVAL.getDefaultValue(),
                        Importance.LOW, KafkaConnectorConfigDoc.TABLE_POLL_INTERVAL_MS_DOC.getDoc())
                .define(TOPIC_PREFIX, Type.STRING, Importance.HIGH, KafkaConnectorConfigDoc.TOPIC_PREFIX_DOC.getDoc())
                .define(TIMESTAMP_DELAY_INTERVAL_MS, Type.LONG, TIMESTAMP_DELAY_INTERVAL_MS_DEFAULT, Importance.HIGH,
                        KafkaConnectorConfigDoc.TIMESTAMP_DELAY_INTERVAL_MS_DOC.getDoc());

    }
}
