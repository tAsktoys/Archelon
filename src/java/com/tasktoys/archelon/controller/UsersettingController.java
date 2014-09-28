/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.data.entity.User.Builder;
import com.tasktoys.archelon.service.UserService;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author YuichiroSato
 * @since 0.1
 * @version 0.1
 */
@Controller
@RequestMapping(value = "/usersetting")
@SessionAttributes(UserSession.SESSION_NAME)
public class UsersettingController {

    @Autowired
    private UserService userService;
    static final Logger log = Logger.getLogger(UsersettingController.class.getName());

    /**
     * User setting view.
     */
    protected final static String VIEW = "usersetting";
    protected static final String REDIRECT = "redirect:/" + VIEW;

    private static final String ID = "id";
    private static final String CULLENT_VALUE_PREFIX = "current_";

    private static final String PASSWORD = "user_password";

    private static final String USERSETTING = "usersetting";
    private static final String PASSWORD_RESET = "passwordreset";
    private static final String WITHDRAW = "withdraw";

    private enum Param {

        USER_NAME, NEW_USER_PASSWORD, NEW_USER_PASSWORD_R, EMAIL, DESCRIPTION, BIRTHDATE, LOCATION,
        AFFILIATE, URL, TWITTERID, FACEBOOKID;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private enum Error {

        NAME_MISS, USER_NAME_DUPLICATE, PASSWORD_MISS, PASSWORD_MISSMATCH,
        EMAIL_MISS, EMAIL_INVALID, BIRTHDATE_FUTURE, BIRTHDATE_INVALID,
        USERSETTING_PASSWORD_MISSMATCH, PASSWORDRESET_PASSWORD_MISSMATCH,
        WITHDRAW_PASSWORD_MISSMATCH;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handleEmptyRequest(Model model) {
        model.addAttribute(ID, "Guest");
        return VIEW;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String handleIdRequest(@PathVariable String id, Model model, UserSession userSession,
            RedirectAttributes redirect) {
        if (!id.equals(userSession.getName())) {
            redirect.addFlashAttribute(ErrorController.ATTR_MESSAGE, "error.access.permission");
            return ErrorController.REDIRECT;
        }
        model.addAttribute(ID, id);
        setUserValueToForm(model, userSession.getUser());
        return VIEW;
    }

    @RequestMapping(value = USERSETTING, method = RequestMethod.POST)
    public String handleUserSetting(@RequestParam Map<String, String> params, Model model, UserSession userSession,
            RedirectAttributes redirect) {
        model.addAttribute(ID, userSession.getName());
        redirect.addAttribute(ID, userSession.getName());
        if (isCorrectPassword(userSession, params)) {
            return updateUserInformation(redirect, params, userSession);
        } else {
            setUserValueToForm(model, userSession.getUser());
            return errorHandle(model, Error.USERSETTING_PASSWORD_MISSMATCH.toString());
        }
    }

    @RequestMapping(value = PASSWORD_RESET, method = RequestMethod.POST)
    public String handlePasswordReset(@RequestParam Map<String, String> params, Model model, UserSession userSession) {
        model.addAttribute(ID, userSession.getName());
        if (isCorrectPassword(userSession, params)) {
            return resetPassword(model, params, userSession);
        } else {
            setUserValueToForm(model, userSession.getUser());
            return errorHandle(model, Error.PASSWORDRESET_PASSWORD_MISSMATCH.toString());
        }
    }

    @RequestMapping(value = WITHDRAW, method = RequestMethod.POST)
    public String handleWithdraw(@RequestParam Map<String, String> params, Model model, UserSession userSession) {
        model.addAttribute(ID, userSession.getName());
        if (isCorrectPassword(userSession, params)) {
            return setUserStateToWithdrew(userSession);
        } else {
            setUserValueToForm(model, userSession.getUser());
            return errorHandle(model, Error.WITHDRAW_PASSWORD_MISSMATCH.toString());
        }
    }

    private void setUserValueToForm(Model model, User user) {
        Map<String, String> map = user.toSecureMap();
        for (String key : map.keySet()) {
            model.addAttribute(CULLENT_VALUE_PREFIX + key, map.get(key));
            log.log(Level.INFO, map.get(key));
        }
    }

    private boolean isCorrectPassword(UserSession userSession, Map<String, String> params) {
        User user = userSession.getUser();
        String password = params.get(PASSWORD);
        return user.isValidPasswordWithPlaneString(password);
    }

    private String updateUserInformation(RedirectAttributes model,
            Map<String, String> params, UserSession userSession) {

        // Get required fields.
        String userName = params.get(Param.USER_NAME.toString());
        String email = params.get(Param.EMAIL.toString());

        // Error check
        if (userName == null || userName.isEmpty()) {
            return errorHandle(model, Error.NAME_MISS.toString());
        }
        if (!userSession.getName().equals(userName) && userService.findUserByName(userName) != null) {
            return errorHandle(model, Error.USER_NAME_DUPLICATE.toString());
        }
        if (email == null || email.isEmpty()) {
            return errorHandle(model, Error.EMAIL_MISS.toString());
        }
        if (!Builder.isValidEmailAddress(email)) {
            return errorHandle(model, Error.EMAIL_INVALID.toString());
        }

        // Get option fields.
        String description = params.get(Param.DESCRIPTION.toString());
        String birthdate = params.get(Param.BIRTHDATE.toString());
        String location = params.get(Param.LOCATION.toString());
        String affiliate = params.get(Param.AFFILIATE.toString());
        String url = params.get(Param.URL.toString());
        String twitterId = params.get(Param.TWITTERID.toString());
        String facebookId = params.get(Param.FACEBOOKID.toString());

        // Error check
        if (birthdate != null && !birthdate.isEmpty()) {
            if (!Builder.isHtmlDateFormat(birthdate)) {
                return errorHandle(model, Error.BIRTHDATE_INVALID.toString());
            }
            if (Builder.isFuture(birthdate)) {
                return errorHandle(model, Error.BIRTHDATE_FUTURE.toString());
            }
        }

        Builder builder = new Builder()
                .id(userSession.getUser().getId())
                .name(userName)
                .plainTextPassword(params.get(PASSWORD))
                .email(email)
                .state(User.State.ACTIVE)
                .nonEmptyDescription(description)
                .nonEmptyBirthdate(birthdate)
                .nonEmptyLocation(location)
                .nonEmptyAffiliate(affiliate)
                .nonEmptyUrl(url);
        builder = twitterOAuth(twitterId, builder);
        builder = facebookOAuth(facebookId, builder);

        User current = builder.build();
        userService.updateUser(current);
        userSession.setUser(userService.findUserById(userSession.getUser().getId()));
        setUserValueToForm(model, userSession.getUser());
        return UserController.REDIRECT + "/" + current.getName();
    }

    private String errorHandle(Model model, String error_name) {
        model.addAttribute(error_name, true);
        return VIEW;
    }

    // TODO: implement
    private Builder twitterOAuth(String id, Builder builder) {
        return builder;
    }

    // TODO: implement
    private Builder facebookOAuth(String id, Builder builder) {
        return builder;
    }

    private String resetPassword(Model model, Map<String, String> params, UserSession userSession) {
        String userPassword = params.get(Param.NEW_USER_PASSWORD.toString());
        String userPasswordRepeat = params.get(Param.NEW_USER_PASSWORD_R.toString());

        // Error check
        if (userPassword == null || userPassword.isEmpty()) {
            return errorHandle(model, Error.PASSWORD_MISS.toString());
        }
        if (userPasswordRepeat == null || userPasswordRepeat.isEmpty()) {
            return errorHandle(model, Error.PASSWORD_MISS.toString());
        }
        if (!userPassword.equals(userPasswordRepeat)) {
            return errorHandle(model, Error.PASSWORD_MISSMATCH.toString());
        }

        User current = new Builder(userSession.getUser())
                .plainTextPassword(userPassword)
                .build();

        userService.updateUser(current);
        userSession.setUser(userService.findUserById(userSession.getUser().getId()));
        setUserValueToForm(model, userSession.getUser());
        return UserController.REDIRECT + "/" + current.getName();
    }

    private String setUserStateToWithdrew(UserSession userSession) {
        User withdrew = new Builder(userSession.getUser())
                .state(User.State.WITHDREW)
                .build();

        userService.updateUser(withdrew);
        userSession.clear();
        return IndexController.VIEW;
    }
}
