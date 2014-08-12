/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;

/**
 * Provides session information.
 * 
 * @author mikan
 * @since 0.1
 */
public class UserSession {
    
    private User user;
    public static final String SESSION_NAME = "hoge";
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
    
    public String getName() {
        if (user == null)
            return null;
        return user.getName();
    }
    
    public void clear() {
        user = null;
    }
}
