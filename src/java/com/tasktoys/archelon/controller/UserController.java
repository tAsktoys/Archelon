/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of <code>user.jsp</code>.
 * 
 * @author mikan
 * @author ysato
 * @since 0.1
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    private final String NAME = "name";
    private final String user_profile = "user_profile";
    private final String user_information = "user_information";
    private final String user_activity = "user_activity";
    
    @RequestMapping(method = RequestMethod.GET)
    public String getUserGuest(Model model) {
        model.addAttribute(NAME, "Guest");
        updatePage(model);
        return "user";
    }
    
    @RequestMapping(value="{name}", method = RequestMethod.GET)
    public String getUser(@PathVariable String name, Model model) {
        
        User user = userService.findUserWithName(name);
        if (user == null) {
            model.addAttribute(NAME, "[NOT FOUND]");
            return "user";
        }
        model.addAttribute(NAME, name);
        updatePage(model);
        return "user";
    }
    
    private void updatePage(Model model) {
        updateUserInformation(model);
        updateUserActivity(model);
    }
    
    private void updateUserInformation(Model model) {
        String profile = "one comment: I am a high school student in Isikawa. I have an interest in Computer Science. I have been challenging to invent a web service by Common Lisp :-)";
        model.addAttribute(user_profile, profile);
        
        List<String> list = new ArrayList<>();
        list.add("nisino");
        list.add("16");
        list.add("High School");
        model.addAttribute(user_information, list);
    }
    
    private void updateUserActivity(Model model) {
        List<String> list = new ArrayList<>();
        list.add("\"hoge!hoge!hoge!\"");
        list.add("\"Hooaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1\"");
        list.add("Discussion title \"Foo\" is made");
        model.addAttribute(user_activity, list);
    }

}
