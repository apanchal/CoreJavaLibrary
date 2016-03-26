package com.nividous.library.common.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.inclever.library.common.SystemPropertiesPool;

public class SystemPropertiesPoolTest {

    @Test
    public void testGetJavaVersion() {
        String javaVersion = SystemPropertiesPool.getJavaVersion();
        assertNotNull(javaVersion);
    }

    @Test
    public void testGetTempDirectory() {
        String tempDir = SystemPropertiesPool.getTempDirectory();
        assertNotNull(tempDir);
    }

    @Test
    public void testGetFileSeparator() {
        String fileSeparator = SystemPropertiesPool.getFileSeparator();
        assertNotNull(fileSeparator);
    }

    @Test
    public void testGetPathSeparator() {
        String pathSeparator = SystemPropertiesPool.getPathSeparator();
        assertNotNull(pathSeparator);
    }

    @Test
    public void testGetLineSeparator() {
        String lineSeparator = SystemPropertiesPool.getLineSeparator();
        assertNotNull(lineSeparator);
    }

}
