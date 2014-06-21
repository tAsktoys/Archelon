/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of /login in header.jspf
 *
 * @author mikan
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    
    @RequestMapping(method = RequestMethod.POST)
    public String getLogin(Model model) {
        return "login";
    }
}
