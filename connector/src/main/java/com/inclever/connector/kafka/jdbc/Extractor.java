/**
 * 
 */
package com.inclever.connector.kafka.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;

/**
 * @author apanchal
 *
 */
public abstract class Extractor implements Comparable<Extractor> {

    protected final ExtractorMode extractorMode;
    protected final String tableName;
    protected final String query;
    protected final String topicPrefix;
    protected long lastUpdate;
    protected PreparedStatement stmt;
    protected ResultSet resultSet;
    protected Schema schema;

    /**
     * 
     * @author apanchal
     *
     */
    public enum ExtractorMode {
        TABLE, // Copying entire tables, with queries constructed automatically
        QUERY // User-specified query
    }

    /**
     * 
     * @param extractorMode
     * @param nameOrQuery
     * @param topicPrefix
     */
    public Extractor(ExtractorMode extractorMode, String nameOrQuery, String topicPrefix) {
        this.extractorMode = extractorMode;
        this.tableName = extractorMode == ExtractorMode.TABLE ? nameOrQuery : null;
        this.query = extractorMode == ExtractorMode.QUERY ? nameOrQuery : null;
        this.topicPrefix = topicPrefix;
        this.lastUpdate = 0;
    }

    public PreparedStatement getOrCreatePreparedStatement(Connection db) throws SQLException {
        if (stmt != null) {
            return stmt;
        }
        createPreparedStatement(db);
        return stmt;
    }

    protected abstract void createPreparedStatement(Connection db) throws SQLException;

    public boolean querying() {
        return resultSet != null;
    }

    public void maybeStartQuery(Connection db) throws SQLException {
        if (resultSet == null) {
            stmt = getOrCreatePreparedStatement(db);
            resultSet = executeQuery();
            schema = DataConverter.convertSchema(tableName, resultSet.getMetaData());
        }
    }

    protected abstract ResultSet executeQuery() throws SQLException;

    public boolean next() throws SQLException {
        return resultSet.next();
    }

    public abstract SourceRecord extractRecord() throws SQLException;

    public void close(long now) throws SQLException {
        if (resultSet != null)
            resultSet.close();
        resultSet = null;
        // TODO: Can we cache this and quickly check that it's identical for the
        // next query
        // instead of constructing from scratch since it's almost always the
        // same
        schema = null;

        lastUpdate = now;
    }

    @Override
    public int compareTo(Extractor other) {
        if (this.lastUpdate < other.lastUpdate) {
            return -1;
        } else if (this.lastUpdate > other.lastUpdate) {
            return 1;
        } else {
            return this.tableName.compareTo(other.tableName);
        }
    }

    public long getLastUpdate() {
        return lastUpdate;
    }
}
