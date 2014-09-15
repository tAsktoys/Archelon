/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */

package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.ActivityDao;
import com.tasktoys.archelon.data.entity.Activity;
import com.tasktoys.archelon.service.ActivityService;
import java.util.ArrayList;
import java.util.Collections;
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
    public Map<String, List<Map<String, String>>> createActivities(String name, int n) {
        return Collections.singletonMap(name, toMapList(activityDao.findLatestActivities(n)));
    }
    
    private List<Map<String, String>> toMapList(List<Activity> activityList) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Activity activity : activityList) {
            mapList.add(toMap(activity));
        }
        return mapList;
    }
    
    private Map<String, String> toMap(Activity activity) {
        switch (activity.getActivityType()) {
            case CREATE_DISCUSSION:
                return createDiscussionActivity(activity);
            case SOLVE_DISCUSSION:
                return solveDiscussionActivity(activity);
            case CLOSE_DISCUSSION:
                return closeDiscussionActivity(activity);
        }
        return null;
    }
    
    private Map<String, String> createDiscussionActivity(Activity activity) {
        Map<String, String> map = new HashMap<>();
        map.put("time", activity.getCreatedTime().toString());
        map.put("act", "User " + activity.getUserId() +  " creates disuccsion " + activity.getTargetDiscussionId());
        return map;
    }
    
    private Map<String, String> solveDiscussionActivity(Activity activity) {
        Map<String, String> map = new HashMap<>();
        map.put("time", activity.getCreatedTime().toString());
        map.put("act", "User " + activity.getUserId() + " solved discussion " + activity.getTargetDiscussionId());
        return map;
    }
    
    private Map<String, String> closeDiscussionActivity(Activity activity) {
        Map<String, String> map = new HashMap<>();
        map.put("time", activity.getCreatedTime().toString());
        map.put("act", "User " + activity.getUserId() + " closed discussion " + activity.getTargetDiscussionId());
        return map;
    }
}
