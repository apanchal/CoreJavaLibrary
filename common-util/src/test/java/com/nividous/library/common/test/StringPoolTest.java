package com.nividous.library.common.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.inclever.library.common.StringPool;

public class StringPoolTest {

    @Test
    public void testGetAsciiChars() {
        for (String aciiChar : StringPool.getAsciiChars()) {
            assertNotNull(aciiChar);
        }
    }

}
