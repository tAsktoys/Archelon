/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
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
 * Controller of discussion.jsp
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.2
 */
@Controller
@RequestMapping(value = "/discussion")
public class DiscussionController {

    private static final String ID = "id";

    private static final String THEME = "theme";
    private static final String DISCUSSION_LOG = "discussion_log";
    private static final String TYPE = "type";
    private static final String ICON = "icon";
    private static final String USERPAGE = "userpage";
    private static final String USERNAME = "username";
    private static final String MESSAGE = "message";
    private static final String DISCUSSION = "discussion";
    private static final String POSTEDMESSAGE = "postedMessage";

    @RequestMapping(value = "{" + ID + "}", method = RequestMethod.GET)
    public String getDiscussion(@PathVariable String id, Model model) {
        updateDiscussionTheme(model);
        updateDiscussionLog(model);
        model.addAttribute(ID, id);
        return DISCUSSION;
    }

    @RequestMapping(method = RequestMethod.POST, params = POSTEDMESSAGE)
    public String getPostedMessage(@RequestParam Map<String, String> params, Model model) {
        updateDiscussionTheme(model);

        List<HashMap<String, String>> list = new ArrayList<>();

        list.add(putRecode());
        list.add(putRecode());

        HashMap<String, String> map = new HashMap<>();
        map.put(TYPE, "me");
        map.put(ICON, "hoge");
        map.put(USERPAGE, "/archelon/user/777");
        map.put(USERNAME, "Kato");
        map.put(MESSAGE, params.get(POSTEDMESSAGE));

        list.add(map);

        model.addAttribute(DISCUSSION_LOG, list);

        return DISCUSSION;
    }

    private void updateDiscussionTheme(Model model) {
        model.addAttribute(THEME, "What is the best HOGE?");
    }

    private void updateDiscussionLog(Model model) {
        List<HashMap<String, String>> list = new ArrayList<>();

        list.add(putRecode());
        list.add(putRecode());

        model.addAttribute(DISCUSSION_LOG, list);
    }

    private HashMap<String, String> putRecode() {
        HashMap<String, String> map = new HashMap<>();
        map.put(TYPE, "others");
        map.put(ICON, "hoge");
        map.put(USERPAGE, "/Archelon/user/7777");
        map.put(USERNAME, "Sato");
        map.put(MESSAGE, "Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! Hoge!Hoge! ");
        return map;
    }
}
