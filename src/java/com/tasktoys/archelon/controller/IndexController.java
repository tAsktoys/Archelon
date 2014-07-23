/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    private static final String MAIN_CATEGORY = "main_category";
    private static final String SUB_CATEGORY = "sub_category";

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        List<String> main_category_list = new ArrayList<>();
        main_category_list.add("math");
        main_category_list.add("physics");
        model.addAttribute(MAIN_CATEGORY, main_category_list);
        
        List<String> sub_category_list = new ArrayList<>();
        sub_category_list.add("school");
        sub_category_list.add("high school");
        model.addAttribute(SUB_CATEGORY, sub_category_list);
        return VIEW;
    }
    
    @RequestMapping(method = RequestMethod.POST, params = MAIN_CATEGORY)
    public String chooseCategory(@RequestParam Map<String, String> params, Model model) {
        List<String> main_category_list = new ArrayList<>();
        main_category_list.add("math");
        main_category_list.add("physics");
        model.addAttribute(MAIN_CATEGORY, main_category_list);
        
        List<String> sub_category_list = new ArrayList<>();
        sub_category_list.add(params.get(MAIN_CATEGORY) + " school");
        sub_category_list.add(params.get(MAIN_CATEGORY) + " high school");
        model.addAttribute(SUB_CATEGORY, sub_category_list);
        return VIEW;
    }
}
