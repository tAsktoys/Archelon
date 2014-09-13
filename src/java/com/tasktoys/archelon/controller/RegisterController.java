/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */

package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.UserService;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author YuichiroSato
 */
@Controller
@RequestMapping(value = "/register")
@SessionAttributes(UserSession.SESSION_NAME)
public class RegisterController {

    @Autowired
    private UserService userService;

    static String VIEW = "register";
    private static final String VIEW_COMPLETE = "complete";
    private static final String DEFAULT_VALUE_SURFFIX = "input_";
    private static Logger log = Logger.getLogger(RegisterController.class.getName());

    private enum Param {

        USER_NAME, USER_PASSWORD, USER_PASSWORD_R, EMAIL, DESCRIPTION, BIRTHDATE, LOCATION, 
        AFFILIATE, URL, TWITTERID, FACEBOOKID;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    
    private enum Error {

        NAME_MISS, USER_NAME_DUPLICATE, PASSWORD_MISS, PASSWORD_MISSMATCH,
        EMAIL_MISS, EMAIL_INVALID, BIRTHDATE_FUTURE, BIRTHDATE_INVALID;
        
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handleGet(Model model, UserSession userSession, RedirectAttributes redirect) {
        if (0 <= userSession.getUser().getId()) {
            redirect.addFlashAttribute(ErrorController.ATTR_MESSAGE, "error.auth.alreadyloggedin");
            return ErrorController.REDIRECT;
        }
        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handlePost(@RequestParam Map<String, String> params, Model model,
            UserSession userSession, RedirectAttributes redirect) {
        User.Builder builder = new User.Builder();
        setInputValueToForm(model, params);
        
        // Get required fields.
        String userName = params.get(Param.USER_NAME.toString());
        String userPassword = params.get(Param.USER_PASSWORD.toString());
        String userPasswordRepeat = params.get(Param.USER_PASSWORD_R.toString());
        String email = params.get(Param.EMAIL.toString());

        if (userName == null || userName.isEmpty())
            return errorHandle(model, Error.NAME_MISS.toString());
        if (userService.findUserByName(userName) != null)
            return errorHandle(model, Error.USER_NAME_DUPLICATE.toString());
        if (userPassword == null || userPassword.isEmpty())
            return errorHandle(model, Error.PASSWORD_MISS.toString());
        if (userPasswordRepeat == null || userPasswordRepeat.isEmpty())
            return errorHandle(model, Error.PASSWORD_MISS.toString());
        if (!userPassword.equals(userPasswordRepeat))
            return errorHandle(model, Error.PASSWORD_MISSMATCH.toString());
        if (email == null || email.isEmpty())
            return errorHandle(model, Error.EMAIL_MISS.toString());
        if (!builder.isValidEmailAddress(email))
            return errorHandle(model, Error.EMAIL_INVALID.toString());

        // Get option fields.
        String description = params.get(Param.DESCRIPTION.toString());
        String birthdate = params.get(Param.BIRTHDATE.toString());
        String location = params.get(Param.LOCATION.toString());
        String affiliate = params.get(Param.AFFILIATE.toString());
        String url = params.get(Param.URL.toString());
        String twitterId = params.get(Param.TWITTERID.toString());
        String facebookId = params.get(Param.FACEBOOKID.toString());
        
        builder.name(userName);
        builder.password(DigestUtils.sha256Hex(userPassword));
        builder.email(email);
        builder.state(User.State.ACTIVE);
        if (description != null && !description.isEmpty())
            builder.description(description);
        if (birthdate != null && !birthdate.isEmpty()) {
            int year, month, day;
            try {
                String[] yyyy_mm_dd = birthdate.split("-");
                year = Integer.parseInt(yyyy_mm_dd[0]);
                // due to the difference of data format
                month = Integer.parseInt(yyyy_mm_dd[1]) - 1;
                day = Integer.parseInt(yyyy_mm_dd[2]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if (isFuture(calendar)) 
                    return errorHandle(model, Error.BIRTHDATE_FUTURE.toString());
                builder.birthdate(calendar.getTime());
            } catch (NumberFormatException e) {
                return errorHandle(model, Error.BIRTHDATE_INVALID.toString());
            }
        }
        if (location != null && !location.isEmpty())
            builder.location(location);
        if (affiliate != null && !affiliate.isEmpty())
            builder.affiliate(affiliate);
        if (url != null && !url.isEmpty())
            builder.url(url);
        if (twitterId != null) {
            builder.twitterId(null); // stub
            builder.twitterToken(null);
            builder.twitterSecret(null);
        }
        if (facebookId != null) {
            builder.facebookId(null); // stub
            builder.facebookToken(null);
            builder.facebookSecret(null);
        }
        
        try{
            userService.insertUser(builder.buildForInsert());
            userSession.setUser(userService.findUserByName(userName));
            return VIEW_COMPLETE;
        } catch (IllegalStateException e) {
            return VIEW;
        }
    }

    @ModelAttribute(value = UserSession.SESSION_NAME)
    public UserSession setUpUserSession() {
        log.log(Level.INFO, "user logged in");
        return new UserSession();
    }
    
    private void setInputValueToForm(Model model, Map<String, String> params) {
        for(String key : params.keySet()) {
            model.addAttribute(DEFAULT_VALUE_SURFFIX + key, params.get(key));
        }
    }
    
    private String errorHandle(Model model, String error_name) {
        model.addAttribute(error_name, true);
        return VIEW;
    }

    private boolean isFuture(Calendar calendar) {
        return calendar.compareTo(Calendar.getInstance()) > 0;
    }
}
