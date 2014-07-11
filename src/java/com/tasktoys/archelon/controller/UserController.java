/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.UserService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of <code>user.jsp</code>.
 *
 * @author mikan
 * @author ysato
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final String ID = "id";
    private final String NAME = "name";
    private final String PROFILE = "profile";
    private final String OVERVIEW = "overview";
    private final String user_activity = "user_activity";

    @RequestMapping(method = RequestMethod.GET)
    public String getUserGuest(Model model) {
        model.addAttribute(NAME, "Guest");
        updateUserInformation(model, null);
        updateUserActivity(model);
        return "user";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public String getUser(@PathVariable String name, Model model) {

        User user = userService.findUserByName(name);
        if (user == null) {
            model.addAttribute(NAME, "[NOT FOUND]");
            return "user";
        }
        model.addAttribute(NAME, name);
        updateUserInformation(model, user);
        updateUserActivity(model);
        return "user";
    }

    private void updateUserInformation(Model model, User user) {
        model.addAttribute(PROFILE, user.getProfile());

        long id = user.getId();
        String name = user.getName();
        Date birthdate = user.getBirthdate();
        String place = user.getPlace();

        List<String> list = new ArrayList<>();
        list.add(id < 0 ? "(N/A)" : "ID: " + Long.toString(id));
        list.add(name == null ? "(N/A)" : "Name: " + name);
        list.add(birthdate == null ? "(N/A)" : "Age: " + calcAge(birthdate));
        list.add(place == null ? "(N/A)" : place);
        model.addAttribute(OVERVIEW, list);
    }

    private void updateUserActivity(Model model) {
        List<String> list = new ArrayList<>();
        list.add("\"hoge!hoge!hoge!\"");
        list.add("\"Hooaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1\"");
        list.add("Discussion title \"Foo\" is made");
        model.addAttribute(user_activity, list);
    }

    private int calcAge(Date birthdate) {
        Calendar current = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthdate);
        int age = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (current.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
            if (current.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
        } else if (current.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            age--;
        }
        return age;
    }
}
