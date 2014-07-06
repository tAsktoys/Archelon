/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.service.impl.HelloServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for {@link HelloServiceImpl}.
 *
 * @author mikan
 */
public class HelloServiceTest {
    
    public HelloServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sayHello method, Normal case.
     */
    @Test
    public void testSayHello_NormalInput() {
        HelloServiceImpl instance = new HelloServiceImpl();
        String name = "test";
        String expResult = "Hello test!";
        String result = instance.sayHello(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of sayHello method, NPE case.
     */
    @Test(expected=NullPointerException.class)
    public void testSayHello_NullInput() {
        HelloServiceImpl instance = new HelloServiceImpl();
        instance.sayHello(null);
        fail();
    }
}
