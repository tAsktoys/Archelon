/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.UserDao;
import com.tasktoys.archelon.data.dao.UserSearchKey;
import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.UserService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides user informations from database.
 * 
 * @author mikan
 */
@Service
public class UserServiceImpl implements UserService, Serializable {
    
    @Autowired
    UserDao userDao;

    @Override
    public User findUser(long id) {
        UserSearchKey searchKey = new UserSearchKey();
        searchKey.setId(id);
        return userDao.findUser(searchKey);
    }

    @Override
    public User findUserWithName(String name) {
        UserSearchKey searchKey = new UserSearchKey();
        searchKey.setName(name);
        return userDao.findUser(searchKey);
    }
    
}
