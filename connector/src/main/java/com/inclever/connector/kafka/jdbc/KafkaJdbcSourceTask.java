/**
 * 
 */
package com.inclever.connector.kafka.jdbc;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author apanchal
 *
 */
public class KafkaJdbcSourceTask extends SourceTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJdbcSourceTask.class);
	/**
	 * 
	 */
	public KafkaJdbcSourceTask() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.apache.kafka.connect.connector.Task#version()
	 */
	@Override
	public String version() {
		// TODO Auto-generated method stub
		return AppInfoParser.getVersion();
	}

	/* (non-Javadoc)
	 * @see org.apache.kafka.connect.source.SourceTask#poll()
	 */
	@Override
	public List<SourceRecord> poll() throws InterruptedException {
		LOGGER.debug("Polling...");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.kafka.connect.source.SourceTask#start(java.util.Map)
	 */
	@Override
	public void start(Map<String, String> arg0) {
		LOGGER.debug("Starting {}...",KafkaJdbcSourceTask.class.getName());

	}

	/* (non-Javadoc)
	 * @see org.apache.kafka.connect.source.SourceTask#stop()
	 */
	@Override
	public void stop() {
		LOGGER.debug("Stopping {}...",KafkaJdbcSourceTask.class.getName());

	}

}
