/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */

package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Activity;
import java.util.List;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
public interface ActivityDao {
    
    public List<Activity> findLatestActivities(int n);
}
