/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Convert "/archelon/*" request to configured path.
 * 
 * <p>The configured path was provided by <code>context.properties</code>.</p>
 *
 * @author mikan
 */
@Controller
@RequestMapping(value = "/archelon")
public class ContextPathConvertController {
    
    private static final String CONTEXT_PATH = "contextpath";
    
    @Autowired
    private Properties properties;
    
    @RequestMapping(method = RequestMethod.GET)
    public View handleGet() {
        return new RedirectView(properties.getProperty(CONTEXT_PATH));
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public View handleGetWithName(@PathVariable String name) {
        return new RedirectView(properties.getProperty(CONTEXT_PATH) + name);
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public View handlePostWithName(@PathVariable String name) {
        return new RedirectView(properties.getProperty(CONTEXT_PATH) + name);
    }
    
    @RequestMapping(value = "/{name1}/{name2}", method = RequestMethod.POST)
    public View handlePostWithName(@PathVariable String name1, @PathVariable String name2) {
        return new RedirectView(properties.getProperty(CONTEXT_PATH) + name1 + "/" + name2);
    }
}
