/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
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
 * @author YuichiroSato
 * @since 0.1
 * @version 0.1
 */
@Controller
@RequestMapping(value = "/usersetting")
public class UsersettingController {

    /**
     * User setting view.
     */
    protected final static String VIEW = "usersetting";

    private static final String USER = "user";
    private static final String USER_INFORMATION = "user_information";
    private static final String USER_PROFILE = "user_profile";
    private static final String ID = "id";
    private static final String INPUTFORM_LIST = "inputform_list";

    private static final String FIELD = "field";
    private static final String ENTITY = "entity";

    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String SCHOOL = "school"; // TODO: replace to "affiliate"

    @RequestMapping(method = RequestMethod.GET)
    public String handleEmptyRequest(Model model) {
        model.addAttribute(ID, "Guest");

        updateForm("Guest", model);

        return VIEW;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String handleIdRequest(@PathVariable String id, Model model) {
        model.addAttribute(ID, id);

        updateForm(id, model);

        return VIEW;
    }

    private void updateForm(String id, Model model) {
        List<Map<String, String>> list = new ArrayList<>();
        list.add(makeMap(NAME, id));
        list.add(makeMap(AGE, "16"));
        list.add(makeMap(SCHOOL, ""));
        model.addAttribute(INPUTFORM_LIST, list);
    }

    private Map<String, String> makeMap(String field, String entity) {
        Map<String, String> map = new HashMap<>();
        map.put(FIELD, field);
        map.put(ENTITY, entity);
        return map;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handlePost(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute(USER_PROFILE, params.get(USER_PROFILE));

        List<String> list = new ArrayList<>();
        list.add(params.get(NAME));
        list.add(params.get(AGE));
        list.add(params.get(SCHOOL));
        model.addAttribute(USER_INFORMATION, list);
        return USER;
    }
}
