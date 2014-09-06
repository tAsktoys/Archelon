/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Convert "/archelon/*" request to "/*".
 *
 * @author mikan
 */
@Controller
@RequestMapping(value = "/archelon")
public class ContextPathConvertController {
    
    @RequestMapping(method = RequestMethod.GET)
    public View handleGet() {
        return new RedirectView("/");
    }
    
    @RequestMapping(value = "value", method = RequestMethod.GET)
    public View handleGetdWithValue(@PathVariable String value) {
        return new RedirectView("/" + value);
    }
}
