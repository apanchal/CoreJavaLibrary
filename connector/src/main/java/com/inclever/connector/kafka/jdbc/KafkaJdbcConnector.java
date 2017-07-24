/**
 * 
 */
package com.inclever.connector.kafka.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inclever.connector.api.Connector;

/**
 * @author apanchal
 *
 */
public class KafkaJdbcConnector extends SourceConnector implements Connector {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJdbcConnector.class);

	private Map<String, String> configProperties;

	private KafkaJdbcConnectorConfig config;

	private Connection connection;

	/**
	 * 
	 */
	public KafkaJdbcConnector() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.kafka.connect.connector.Connector#config()
	 */
	@Override
	public ConfigDef config() {
	    return KafkaJdbcConnectorConfig.config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.kafka.connect.connector.Connector#start(java.util.Map)
	 */
	@Override
	public void start(Map<String, String> properties) throws ConnectException {
		LOGGER.debug("Starting {} Connector",KafkaJdbcConnector.class.getName());
		try {
			configProperties = properties;
			config = new KafkaJdbcConnectorConfig(configProperties);
		} catch (ConfigException configException) {
			LOGGER.debug("Failed to start JdbcConnector due to configuration due to error:", configException);
			throw new ConnectException("Failed to start JdbcConnector due to configuration due to error:",
					configException);
		}

		String databaseURL = config.getString(KafkaJdbcConnectorConfig.CONNECTION_URL);
		LOGGER.debug("Connecting to {}...", databaseURL);

		try {
			connection = DriverManager.getConnection(databaseURL);
		} catch (SQLException sqlException) {
			LOGGER.error("Failed to open connection to {}: {}", databaseURL, sqlException);
			throw new ConnectException("Failed to connect to database using url " + databaseURL + ", due to error ",
					sqlException);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.kafka.connect.connector.Connector#stop()
	 */
	@Override
	public void stop() {
		if(connection != null) {
		    try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.kafka.connect.connector.Connector#taskClass()
	 */
	@Override
	public Class<? extends Task> taskClass() {
		return KafkaJdbcSourceTask.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.kafka.connect.connector.Connector#taskConfigs(int)
	 */
	@Override
	public List<Map<String, String>> taskConfigs(int arg0) {
	    List<Map<String, String>> taskConfigs = new ArrayList<>(1);
	    String query = config.getString(KafkaJdbcConnectorConfig.QUERY);
	    LOGGER.debug("Query to be executed: {}",query);
		return taskConfigs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.kafka.connect.connector.Connector#version()
	 */
	@Override
	public String version() {
		// TODO Auto-generated method stub
	    return AppInfoParser.getVersion();
	}

}
