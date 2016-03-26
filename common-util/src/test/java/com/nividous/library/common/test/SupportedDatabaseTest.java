package com.nividous.library.common.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.inclever.library.common.SupportedDatabase;

public class SupportedDatabaseTest {

    @Test
    public void allSupportedDatabases() {
        for (SupportedDatabase supportedDatabase : SupportedDatabase.values()) {
            assertNotNull(supportedDatabase);
        }
    }

}
