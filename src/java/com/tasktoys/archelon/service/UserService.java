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

    public User findUser(long id);

    public User findUserWithName(String name);
}
