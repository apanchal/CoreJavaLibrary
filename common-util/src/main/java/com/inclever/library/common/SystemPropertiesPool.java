package com.inclever.library.common;

public final class SystemPropertiesPool {

    private static String javaVersion;

    private static String tmpDir;

    private static String fileSeparator;

    private static String pathSeparator;

    private static String lineSeparator;

    /**
     * Creates SystemPropertiesPool
     */
    private SystemPropertiesPool() {
        super();
    }

    static {
        javaVersion = System.getProperty("java.version");
        tmpDir = System.getProperty("java.io.tmpdir");
        fileSeparator = System.getProperty("file.separator");
        pathSeparator = System.getProperty("path.separator");
        lineSeparator = System.getProperty("line.separator");
    }

    /**
     * Java Runtime Environment version
     */
    public static String getJavaVersion() {
        return javaVersion;
    }

    /**
     * Default temp file path
     */
    public static String getTempDirectory() {
        return tmpDir;
    }

    /**
     * File separator ("/" on UNIX)
     */
    public static String getFileSeparator() {
        return fileSeparator;
    }

    /**
     * Path separator (":" on UNIX)
     */
    public static String getPathSeparator() {
        return pathSeparator;
    }

    /**
     * Line separator ("\n" on UNIX)
     */
    public static String getLineSeparator() {
        return lineSeparator;
    }

}
