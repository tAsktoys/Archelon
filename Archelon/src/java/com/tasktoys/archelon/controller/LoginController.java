/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller of /login in header.jspf
 *
 * @author mikan
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    
    private static final String SUBMIT_NORMAL = "normalLogin";
    private static final String SUBMIT_TWITTER = "twitterLogin";
    private static final String SUBMIT_REGISTER = "register";
    private static final String PARAM_USER_ID = "userid";
    private static final String PARAM_USER_PASSWORD = "userpassword";
    private static final String ATTR_ID = "userId";
    private static final String ATTR_PASSWORD = "userPassword";
    private static final String VIEW_LOGIN = "login";
    private static final String VIEW_REGISTER = "register";
    
    @RequestMapping(method = RequestMethod.POST, params = SUBMIT_NORMAL)
    public String getLogin(@RequestParam Map<String, String> params, Model model) {
        String userId = params.get(PARAM_USER_ID);
        model.addAttribute(ATTR_ID, params.get(PARAM_USER_ID));
        model.addAttribute(ATTR_PASSWORD, params.get(PARAM_USER_PASSWORD));
        return VIEW_LOGIN;
    }
    
    @RequestMapping(method = RequestMethod.POST, params = SUBMIT_TWITTER)
    public String getTwitterLogin(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute(ATTR_ID, params.get(PARAM_USER_ID));
        model.addAttribute(ATTR_PASSWORD, params.get(PARAM_USER_PASSWORD));
        return VIEW_LOGIN;
    }
    
    @RequestMapping(method = RequestMethod.POST, params = SUBMIT_REGISTER)
    public String getRegister(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute(ATTR_ID, params.get(PARAM_USER_ID));
        model.addAttribute(ATTR_PASSWORD, params.get(PARAM_USER_PASSWORD));
        return VIEW_REGISTER;
    }
}
