/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.User;

/**
 * Interface of user data operations.
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.1
 */
public interface UserDao {
    
    /**
     * Get max user id.
     * 
     * @return user id or -1 if not found. 
     */
    public long getMaxUserID();
    
    /**
     * Find user entity by user name.
     * 
     * @param name name of user
     * @return user entity, or <code>null</code> if not found.
     */
    public User findUserByName(String name);
    
    /**
     * Find user entity by user name.
     * 
     * @param id id of user
     * @return user entity, or <code>null</code> if not found.
     */
    public User findUserByID(Long id);
    
    /**
     * Insert new user.
     * @param user User to insert
     */
    public void insertUser(User user);

}
