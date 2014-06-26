/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of discussion.jsp
 * 
 * @author mikan
 * @author Yuichiro
 * @since 0.2
 */
@Controller
@RequestMapping(value = "/discussion")
public class DiscussionController {

    private static final String THEME = "theme";
    private static final String DISCUSSION_LOG = "discussion_log";
    private static final String TYPE = "type";
    private static final String ICON = "icon";
    private static final String USERPAGE = "userpage";
    private static final String USERNAME = "username";
    private static final String MESSAGE = "message";
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getDiscussion(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute(THEME, "What is the best HOGE?");
        
        updateDiscussionLog(model);
        return "discussion";
    }
    
    private void updateDiscussionLog(Model model) {
        List<HashMap<String,String>> list = new ArrayList<>();
        
        list.add(putRecode());
        list.add(putRecode());
        
        model.addAttribute(DISCUSSION_LOG, list);
    }
    
    private HashMap<String, String> putRecode() {
        HashMap<String,String> map = new HashMap<>();
        map.put(TYPE, "others");
        map.put(ICON, "hoge");
        map.put(USERPAGE, "../user/777");
        map.put(USERNAME, "Sato");
        map.put(MESSAGE, "Hoge!Hoge!");
        return map;
    }
}
