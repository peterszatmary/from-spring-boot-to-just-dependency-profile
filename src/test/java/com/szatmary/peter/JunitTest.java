package com.szatmary.peter;

import org.junit.Assert;
import org.junit.Test;

/**
 * test used in both profiles
 */
public class JunitTest {

    @Test
    public void testIt() {
        Assert.assertTrue(true); // this test always OK
    }

}
