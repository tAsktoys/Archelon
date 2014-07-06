/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller of  search 
 * @author bearing
 * @verson 0.1
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private static final String ATTR_SEARCH_WWORD = "attrSearchWword";
    private static final String PARAM_SEARCH_WORD = "searchWord";
    private static final String VIEW_SEARCH = "search";
    private static final String SUBMIT_SEARCH = "submitsearch";    
    
    @RequestMapping(method=RequestMethod.GET,params = SUBMIT_SEARCH)
    public String getSearchResult(@RequestParam Map<String ,String> params,Model model){
        //System.out.println("test");
        String searchWord = params.get(PARAM_SEARCH_WORD);
        model.addAttribute(ATTR_SEARCH_WWORD, searchWord);
        return VIEW_SEARCH;
    }


}
