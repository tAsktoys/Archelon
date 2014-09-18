/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */

package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
public interface ActivityService {
    
    public Map<String, List<Map<String, Object>>> createActivities(String name, int n);
    public Map<String, List<Map<String, Object>>> createActivities(String name, User user, int n);
}
