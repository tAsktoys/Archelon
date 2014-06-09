/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of discussion.jsp
 * 
 * @author mikan
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/discussion")
public class DiscussionController {

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getDiscussion(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "discussion";
    }
}
