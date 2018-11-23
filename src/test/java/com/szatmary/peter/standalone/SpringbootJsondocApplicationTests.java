package com.szatmary.peter.standalone;

import com.szatmary.peter.SpringbootJsondocApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * this test class have for purpose compilation errors if you
 * use just-dependency profile to build
 *
 *  in profile just-dependency is ignored in testCompile and test phase.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootJsondocApplication.class)
public class SpringbootJsondocApplicationTests {

    @Test
    public void contextLoads() {

    }
}