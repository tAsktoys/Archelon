/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;

/**
 * Provides session information.
 * 
 * @author mikan
 */
public class UserSession {
    
    private User user;
    
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
