package com.inclever.library.configuration;

import java.util.List;

public class LibraryConfiguration {
    /**
     * The id to use when retreiving using spring
     */
    public static final String BEAN_ID = "configId";

    private boolean isAuditOn = false;

    /**
     * Holds a list of Message Bundles
     */
    private List<String> loggingMessagesBundles = null;

    /**
     * Returns the list of logging Message Bundles.
     * 
     * @return List Returns the m_loggingMessagesBundles.
     */
    public List<String> getLoggingMessagesBundles() {
        return loggingMessagesBundles;
    }

    /**
     * Sets the List of Logging Message Bundles
     * 
     * @param loggingMessagesBundles
     *            The loggingMessagesBundles to set.
     */
    public void setLoggingMessagesBundles(List<String> loggingMessagesBundles) {
        this.loggingMessagesBundles = loggingMessagesBundles;
    }

    public boolean isAuditOn() {
        return isAuditOn;
    }

    public void setAuditOn(String isAuditOn) {
        if (isAuditOn != null && !isAuditOn.isEmpty()) {
            this.isAuditOn = Boolean.parseBoolean(isAuditOn);
        }
    }
}
