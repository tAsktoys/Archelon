/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.User;

/**
 *
 * @author mikan
 */
public interface UserService {

    public User findUserByName(String name);
    public void insertUser(User user);
    public void updateUser(User user);
}
