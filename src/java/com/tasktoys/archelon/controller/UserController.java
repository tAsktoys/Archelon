/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.UserService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author YuichiroSato
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    /**
     * User view.
     */
    protected static final String VIEW = "user";

    @Autowired
    private UserService userService;

    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String OVERVIEW = "overview";
    private final String user_activity = "user_activity";

    //Spring message values of each user infomation.
//    private final String ID_LABEL = "";
    private final String STATE_LABEL = "user.label.state";
    private final String NAME_LABEL = "name ";
    private final String EMAIL_LABEL = "user.label.email";
    private final String BIRTHDATE_LABEL = "user.label.age";
    private final String LOCATION_LABEL = "user.label.location";
    private final String AFFILIATE_LABEL = "user.label.affiliate";
    private final String URL_LABEL = "user.label.url";
    private final String TWITTER_ID_LABEL = "user.label.twitter";
    private final String FACEBOOK_ID_LABEL = "user.label.facebook";

    @RequestMapping(method = RequestMethod.GET)
    public String handleEmptyRequest(Model model) {
        model.addAttribute(NAME, "Guest");
        updateUserInformation(model, null);
        updateUserActivity(model);
        return VIEW;
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public String handleNameRequest(@PathVariable String name, Model model) {

        User user = userService.findUserByName(name);
        if (user == null) {
            model.addAttribute(NAME, "[NOT FOUND]");
            return "user";
        }
        model.addAttribute(NAME, name);
        updateUserInformation(model, user);
        updateUserActivity(model);
        return VIEW;
    }

    private void updateUserInformation(Model model, User user) {
        model.addAttribute(DESCRIPTION, user.getDescription());
        List<Map<String, String>> mls = new ArrayList<>();
        mls.add(makeLabelValueMap(STATE_LABEL, user.getState().toString()));
        mls.add(makeLabelValueMap(EMAIL_LABEL, user.getEmail()));
        String birthdate = user.getBirthdate() == null ? null : String.valueOf(calcAge(user.getBirthdate())); 
        mls.add(makeLabelValueMap(BIRTHDATE_LABEL, birthdate));
        mls.add(makeLabelValueMap(LOCATION_LABEL, user.getLocation()));
        mls.add(makeLabelValueMap(AFFILIATE_LABEL, user.getAffiliate()));
        mls.add(makeLabelValueMap(URL_LABEL, user.getUrl()));
        String twitterId = user.getTwitter() == null ? null : user.getTwitter().getId();
        mls.add(makeLabelValueMap(TWITTER_ID_LABEL, twitterId));
        String facebookId = user.getFacebook() == null ? null : user.getFacebook().getId();
        mls.add(makeLabelValueMap(FACEBOOK_ID_LABEL, facebookId));
        model.addAttribute(OVERVIEW, mls);
//
//        long id = user.getId();
//        User.State state = user.getState();
//        String name = user.getName();
//        Date birthdate = user.getBirthdate();
//        String location = user.getLocation();
//        String affiliate = user.getAffiliate();
//        String url = user.getUrl();
//        OAuthAccount twitter = user.getTwitter();
//        String twitterId = twitter != null ? twitter.getId() : null;
//
//        List<String> list = new ArrayList<>();
//        list.add(id < 0 ? "(N/A)" : "ID: " + Long.toString(id));
//        list.add("State: " + state.toString());
//        list.add(name == null ? "(N/A)" : "Name: " + name);
//        list.add(birthdate == null ? "(N/A)" : "Age: " + calcAge(birthdate));
//        list.add(location == null ? "(N/A)" : location);
//        list.add(affiliate == null ? "(N/A)" : affiliate);
//        list.add(url == null ? "(N/A)" : url);
//        list.add(twitterId == null ? "(N/A)" : "@" + twitterId);
//        model.addAttribute(OVERVIEW, list);
    }

    private Map<String, String> makeLabelValueMap(String label, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("label", label);
        map.put("value", value);
        return map;
    }

    private void updateUserActivity(Model model) {
        List<String> list = new ArrayList<>();
        list.add("\"hoge!hoge!hoge!\"");
        list.add("\"Hooaaaaaaaaa aaaaaaaa aaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaa  aaaa aaaaaaa aaaaaaaaa !!!!!!!!!! !!!!!!!!!!!! !!!!!!!! !!!!!!!!!!!! !!!! !!!!!!!!!!!!!1\"");
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
