/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller of search
 *
 * @author MrBearing
 * @author mikan
 * @since 0.1
 * @version 0.1
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    protected static final String VIEW = "search";

    private static final String ATTR_SEARCH_WWORD = "attrSearchWword";
    private static final String PARAM_SEARCH_WORD = "searchWord";
    private static final String SUBMIT_SEARCH = "submitsearch";

    @RequestMapping(method = RequestMethod.GET, params = SUBMIT_SEARCH)
    public String handleRequest(@RequestParam Map<String, String> params, Model model) {
        String searchWord = params.get(PARAM_SEARCH_WORD);
        model.addAttribute(ATTR_SEARCH_WWORD, searchWord);
        return VIEW;
    }

}
