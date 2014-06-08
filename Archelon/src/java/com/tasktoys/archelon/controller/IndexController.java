/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for index.htm.
 *
 * @author mikan
 */
@Controller
@RequestMapping(value="/")
public class IndexController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        return "index";
    } 
}
