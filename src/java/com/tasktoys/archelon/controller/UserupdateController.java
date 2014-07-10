/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    final static private String NAME = "name";
    final static private String AGE = "age";
    final static private String SCHOOL = "school";
    
    @RequestMapping(method = RequestMethod.GET)
    public String getUserGuest(Model model) {
        model.addAttribute(ID, "Guest");
        
        updateForm("Guest", model);
        
        return USERUPDATE;
    }
    
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute(ID, id);
        
        updateForm(id, model);
        
        return USERUPDATE;
    }
    
    private void updateForm(String id, Model model) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        list.add(makeMap(NAME, id));
        list.add(makeMap(AGE,"16"));
        list.add(makeMap(SCHOOL,""));
        model.addAttribute(INPUTFORMLIST, list);
    }
    
    private HashMap<String, String> makeMap(String entity, String data) {
        HashMap<String, String> map = new HashMap<>();
        map.put(ENTITY, entity);
        map.put(DATA, data);
        return map;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String getPostedMessage(@RequestParam Map<String, String> params, Model model) {
        
         List<String> list = new ArrayList<>();
        list.add(params.get(NAME));
        list.add(params.get(AGE));
        list.add(params.get(SCHOOL));
        model.addAttribute("user_information", list);
        return "user";
    }
}
