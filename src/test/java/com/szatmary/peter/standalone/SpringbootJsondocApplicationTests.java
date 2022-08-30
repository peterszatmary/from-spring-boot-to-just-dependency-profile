package com.szatmary.peter.standalone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * this test class have for purpose compilation errors if you
 * use just-dependency profile to build
 *
 *  in profile just-dependency is ignored in testCompile and test phase.
 */
@RunWith(SpringRunner.class)
public class SpringbootJsondocApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertTrue(2 > 0);
    }
}