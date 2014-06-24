/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.tags.Param;

/**
 * Controller of <code>user.jsp</code>.
 * 
 * @author mikan, ysato
 * @since 0.1
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getUserGuest(Model model) {
        model.addAttribute("id", "Guest");
        getUserInformation(model);
        getUserActivity(model);
        return "user";
    }
    
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "user";
    }
    
    private void getUserInformation(Model model) {
        String profile = "one comment: I am a high school student in Isikawa. I have an interest in Computer Science. I have been challenging to invent a web service by Common Lisp :-)";
        model.addAttribute("user_profile", profile);
        
        List<String> list = new ArrayList<>();
        list.add("nisino");
        list.add("16");
        list.add("High School");
        model.addAttribute("user_information", list);
    }
    
    private void getUserActivity(Model model) {
        List<String> list = new ArrayList<>();
        list.add("\"hoge!hoge!hoge!\"");
        list.add("\"Hooaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1\"");
        list.add("Discussion title \"Foo\" is made");
        model.addAttribute("user_activity", list);
    }

}
