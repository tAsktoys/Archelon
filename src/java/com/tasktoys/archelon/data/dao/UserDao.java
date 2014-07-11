/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.User;

/**
 *
 * @author mikan
 */
public interface UserDao {
    
    public User findUser(UserSearchKey searchKey);
}
