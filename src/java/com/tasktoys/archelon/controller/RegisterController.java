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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
@SessionAttributes(types = {UserSession.class})
public class RegisterController {

    @Autowired
    private UserService userService;

    static String VIEW = "register";
    private static final String VIEW_COMPLETE = "complete";
    private static Logger log = Logger.getLogger(RegisterController.class.getName());

    private enum Param {

        USER_NAME, USER_PASSWORD, USER_PASSWORD_R, EMAIL, DESCRIPTION, BIRTHDATE, LOCATION, 
        AFFILIATE, URL, TWITTER_ID, FACEBOOK_ID;

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

        // Get required fields.
        setInputValueToForm(model, params);
        String userName = params.get(Param.USER_NAME.toString());
        String userPassword = params.get(Param.USER_PASSWORD.toString());
        String userPasswordRepeat = params.get(Param.USER_PASSWORD_R.toString());
        String email = params.get(Param.EMAIL.toString());

        if (userName == null || userName.isEmpty())
            return errorHandle(model, "name_miss");
        if (userService.findUserByName(userName) != null)
            return errorHandle(model, "user_name_duplicate");
        if (userPassword == null || userPassword.isEmpty())
            return errorHandle(model, "password_miss");
        if (userPasswordRepeat == null || userPasswordRepeat.isEmpty())
            return errorHandle(model, "password_miss");
        if (!userPassword.equals(userPasswordRepeat))
            return errorHandle(model, "password_missmatch");
        if (email == null || email.isEmpty())
            return errorHandle(model, "email_miss");
        if (!isValidEmailAddress(email))
            return errorHandle(model, "email_invalid");

        // Get option fields.
        String description = params.get(Param.DESCRIPTION.toString());
        String birthdate = params.get(Param.BIRTHDATE.toString());
        String affiliate = params.get(Param.AFFILIATE.toString());
        String url = params.get(Param.URL.toString());
        String twitterId = params.get(Param.TWITTER_ID.toString());
        String facebookId = params.get(Param.FACEBOOK_ID.toString());
        
        User.Builder builder = new User.Builder();
        builder.name(userName);
        builder.password(userPassword);
        builder.email(email);
        builder.state(User.State.ACTIVE.ordinal());
        if (description != null)
            builder.description(description);
        if (birthdate != null) {
            int year, month, day;
            try {
                String[] yyyy_mm_dd = birthdate.split("-");
                year = Integer.parseInt(yyyy_mm_dd[0]);
                month = Integer.parseInt(yyyy_mm_dd[1]);
                day = Integer.parseInt(yyyy_mm_dd[2]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if (isFuture(calendar)) 
                    return errorHandle(model, "birthdate_future");
                builder.birthdate(calendar.getTime());
            } catch (NumberFormatException e) {
                return errorHandle(model, "birthdate_invalid");
            }
        }
        if (affiliate != null)
            builder.affiliate(affiliate);
        if (url != null)
            builder.url(url);
        if (twitterId != null)
            builder.twitterId(twitterId);
        if (facebookId != null)
            builder.facebookId(facebookId);
        
        builder.id(userService.getMaxUserID() + 1);
        try{
            userService.insertUser(builder.build());
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
            model.addAttribute("input_"+key, params.get(key));
        }
    }
    
    private String errorHandle(Model model, String error_name) {
        model.addAttribute(error_name, true);
        return VIEW;
    }

    /**
     * Validate format of e-mail address.
     *
     * @param email e-mail address
     * @return <code>true</code> if email is valid, <code>false</code> otherwise
     * @see InternetAddress#validate()
     */
    private boolean isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }
    
    private boolean isFuture(Calendar calendar) {
        return calendar.compareTo(Calendar.getInstance()) > 0;
    }
}
