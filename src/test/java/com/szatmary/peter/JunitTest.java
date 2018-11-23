package com.szatmary.peter;

import org.junit.Assert;
import org.junit.Test;

/**
 * test used in both profiles.
 */
public class JunitTest {

    @Test
    public void testIt() {
        Assert.assertTrue(2 > 0); // this test always OK
    }
}
