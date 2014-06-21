/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    @RequestMapping(method=RequestMethod.GET)
    public String getSearchResult(Model model){
        
        model.addAttribute("search_word", "");
        return "search";
    }    
}
