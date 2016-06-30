package com.szatmary.peter.standalone;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * this test class have for purpose test that always crash
 * in profile just-dependency is ignored in testCompile and test phase. See
 */
public class StandaloneTest {

    @Test
    public void testIt() {
        Assert.assertTrue(true); // this test always OK
    }

}
