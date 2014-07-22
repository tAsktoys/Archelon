/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of index.jsp
 *
 * @author mikan
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    
    static final String VIEW = "index";

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        return VIEW;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String chooseCategory(Model model) {
        return VIEW;
    }
}
