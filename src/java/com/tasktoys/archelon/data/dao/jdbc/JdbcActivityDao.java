package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.ActivityDao;
import com.tasktoys.archelon.data.entity.Activity;
import com.tasktoys.archelon.data.entity.Activity.ActivityType;
import com.tasktoys.archelon.data.entity.Activity.Builder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
@Repository
public class JdbcActivityDao implements ActivityDao {
    
    @Override
    public List<Activity> findLatestActivities(int n) {
        List<Activity> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ls.add(makeActivity());
        }
        return ls;
    }
    
    @Override
    public List<Activity> findLatestActivitiesByUserId(long userId, int n) {
        List<Activity> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ls.add(makeActivity());
        }
        return ls;
    }
    
    private Activity makeActivity() {
        Builder builder = new Builder();
        long userId = (long)(Math.random() * 3);
        long discussionId = (long)(Math.random() * 1000);
        ActivityType type = (Math.random() < 0.5 ? ActivityType.CREATE_DISCUSSION
                : (Math.random() < 0.5 ? ActivityType.SOLVE_DISCUSSION : ActivityType.CLOSE_DISCUSSION));
        return builder.id(1)
                .activityType(type)
                .userId(userId)
                .createdTime(new Timestamp(System.currentTimeMillis() + (long)(Math.random() * 1000)))
                .targetDiscussionId(discussionId)
                .build();
                
    }
}
