package com.inclever.library.configuration;

public class LibraryConfiguration {
	/**
	 * The id to use when retrieving using spring
	 */
	public static final String BEAN_ID = "configId";

	/**
	 * 
	 */
	private boolean isAuditOn = false;

	/**
	 * 
	 */
	private boolean isEnableSOP = false;

	/**
	 * 
	 * @return
	 */
	public boolean isAuditOn() {
		return isAuditOn;
	}

	/**
	 * 
	 * @param isAuditOn
	 */
	public void setAuditOn(String isAuditOn) {
		if (isAuditOn != null && !isAuditOn.isEmpty()) {
			this.isAuditOn = Boolean.parseBoolean(isAuditOn);
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEnableSOP() {
		return isEnableSOP;
	}

	/**
	 * 
	 * @param isDisableSOP
	 */
	public void setEnableSOP(String isEnableSOP) {
		if (isEnableSOP != null && !isEnableSOP.isEmpty()) {
			this.isEnableSOP = Boolean.parseBoolean(isEnableSOP);
		}
	}
}
