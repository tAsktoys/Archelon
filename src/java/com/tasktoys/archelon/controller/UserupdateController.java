/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Yuichiro
 */
@Controller
@RequestMapping(value="/userupdate")
public class UserupdateController {
    
    final static private String USERUPDATE = "userupdate";
    final static private String ID = "id";
    final static private String INPUTFORMLIST = "input_form_list";
    
    final static private String ENTITY = "entity";
    final static private String DATA = "data";
    
    @RequestMapping(method = RequestMethod.GET)
    public String getUserGuest(Model model) {
        model.addAttribute(ID, "Guest");
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        list.add(makeMap("name", "Kato"));
        list.add(makeMap("age","16"));
        list.add(makeMap("school",""));
        model.addAttribute(INPUTFORMLIST, list);
        
        return USERUPDATE;
    }
    
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute(ID, id);
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        list.add(makeMap("name", id));
        list.add(makeMap("age","16"));
        list.add(makeMap("school",""));
        model.addAttribute(INPUTFORMLIST, list);
        
        return USERUPDATE;
    }
    
    private HashMap<String, String> makeMap(String entity, String data) {
        HashMap<String, String> map = new HashMap<>();
        map.put(ENTITY, entity);
        map.put(DATA, data);
        return map;
    }
}
