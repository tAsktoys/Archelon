/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 拓海
 */
public class ContextConfiguration {
    
    private static final ContextConfiguration INSTANCE = new ContextConfiguration();
    /**
     * context Path
     */
    private String contextPath;
   
    /**
     * Constractor
     */
    private ContextConfiguration() {
        Properties properties = new Properties();
        try {
            
            properties.load(new FileInputStream(new File("./web/WEB-INF/classes/context.properties")));
        } catch (IOException ex) {
            Logger.getLogger(ContextConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            this.contextPath = "/";
        }
        
        contextPath = properties.getProperty("contexPath");
    }
    
    public static ContextConfiguration getInstance() {
        return ContextConfiguration.INSTANCE;
    }
    
    /**
     * Return ccontext
     * @return String ContextPath
     */
    public String readContextPath() {
        return this.contextPath;  
    }
    
    
}
