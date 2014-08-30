/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.controller;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 拓海
 */
public class ContextConfigurationTest {
    
    
    public ContextConfigurationTest() {
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
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void readContextPathTest(){
        String sCurrentDir = new File(".").getAbsoluteFile().getParent();
        System.out.println(sCurrentDir);
        
        ContextConfiguration conf = ContextConfiguration.getInstance();
        String path = conf.readContextPath();
        System.out.println(path);
        assertEquals(path, "/archelon/");
        
    }
    
    
    
}
