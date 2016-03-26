package com.inclever.library.configuration;

/**
 * This class stores variables for the version and build numbers for INCLever
 * library.
 * 
 * @author apanchal
 * @since 2.0
 * 
 */
public abstract class LibraryVersion {

    private static final String copyrightString = "Copyright (c) 2014, 2015 INClever Software.  All rights reserved.";

    private static final String productName = "INCLever Core Infrastructure Library";

    private final static String version = "@(#)2.0@";

    private final static String buildNumber = "@(##)0204@";

    private final static String buildDate = "@(###)08.07.2015 19:17@";

    private final static String buildType = "@(####)GA@";

    /**
     * Create INCLever Version
     */
    private LibraryVersion() {
        super();
    }

    /**
     * 
     * @return
     */
    public static String getCopyrightString() {
        return copyrightString;
    }

    public static String getVersion() {
        return version.replaceAll("@", "").replaceAll("#", "").replace("(", "").replace(")", "");
    }

    public static String getBuildDate() {
        return buildDate.replaceAll("@", "").replaceAll("#", "").replace("(", "").replace(")", "");
    }

    public static String getBuildNumber() {
        return buildNumber.replaceAll("@", "").replaceAll("#", "").replace("(", "").replace(")", "");
    }

    public static String getBuildtype() {
        return buildType.replaceAll("@", "").replaceAll("#", "").replace("(", "").replace(")", "");
    }

    public static String getProductName() {
        return productName;
    }

}
