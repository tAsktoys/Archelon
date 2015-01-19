/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handle error messages and exceptions.
 *
 * @author mikan
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {
    
    protected static final String VIEW = "error";
    protected static final String REDIRECT = "redirect:/" + VIEW;
    protected static final String ATTR_MESSAGE = "message";
    protected static final String ATTR_EXCEPTION = "exception";
    
    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(ModelMap modelMap) {
        if (modelMap.get(ATTR_MESSAGE) == null) {
            return "redirect:/"; // return to index
        }
        return VIEW;
    }
}
