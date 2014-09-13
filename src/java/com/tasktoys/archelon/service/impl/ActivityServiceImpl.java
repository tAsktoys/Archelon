/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */

package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.ActivityDao;
import com.tasktoys.archelon.data.entity.Activity;
import com.tasktoys.archelon.service.ActivityService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;
    
    @Override
    public List<Map<String, String>> createActivities(int n) {
        return toMapList(activityDao.findLatestActivities(n));
    }
    
    private List<Map<String, String>> toMapList(List<Activity> activityList) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Activity activity : activityList) {
            mapList.add(toMap(activity));
        }
        return mapList;
    }
    
    private Map<String, String> toMap(Activity activity) {
        Map<String, String> map = new HashMap<>();
        return map;
    }
    
}
