/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */

package com.tasktoys.archelon.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
public interface ActivityService {
    
    public Map<String, List<Map<String, String>>> createActivities(String name, int n);
}
