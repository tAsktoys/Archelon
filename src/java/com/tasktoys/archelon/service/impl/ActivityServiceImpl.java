/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.ActivityDao;
import com.tasktoys.archelon.data.dao.DiscussionDao;
import com.tasktoys.archelon.data.entity.Activity;
import com.tasktoys.archelon.data.entity.Activity.ActivityType;
import com.tasktoys.archelon.data.entity.Activity.Builder;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.ActivityService;
import com.tasktoys.archelon.service.UserService;
import java.sql.Timestamp;
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
    @Autowired
    private UserService userService;
    @Autowired
    private DiscussionDao discussionDao;

    private static final String TIME = "time";
    private static final String ACT = "act";
    private static final String STRING = "string";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";

    @Override
    public Map<String, List<Map<String, Object>>> createActivities(String name, int n) {
        return Collections.singletonMap(name, toMapList(activityDao.findLatestActivities(n)));
    }

    @Override
    public Map<String, List<Map<String, Object>>> createActivities(String name, User user, int n) {
        return Collections.singletonMap(name, toMapList(activityDao.findLatestActivitiesByUserId(user.getId(), n)));
    }

    @Override
    public void discussionMadeBy(User user) {
        Builder builder = new Builder();
        long authorId = user.getId();
        List<Discussion> discussionList = discussionDao.findLatestDiscussionsByAuthorId(authorId, 1);
        if (0 < discussionList.size()) {
            Discussion madeDiscussion = discussionList.get(0);
            builder.activityType(ActivityType.CREATE_DISCUSSION)
                    .userId(authorId)
                    .createdTime(madeDiscussion.getCreateTime())
                    .targetDiscussionId(madeDiscussion.getId());
            activityDao.insertActivity(builder.buildForInsert());
        }
    }

    private List<Map<String, Object>> toMapList(List<Activity> activityList) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Activity activity : activityList) {
            mapList.add(toMap(activity));
        }
        return mapList;
    }

    private Map<String, Object> toMap(Activity activity) {
        Map<String, Object> subModel = createTime(activity.getCreatedTime());
        switch (activity.getActivityType()) {
            case CREATE_DISCUSSION:
                subModel.put(ACT, createDiscussionActivity(activity));
                return subModel;
            case SOLVE_DISCUSSION:
                subModel.put(ACT, solveDiscussionActivity(activity));
                return subModel;
            case CLOSE_DISCUSSION:
                subModel.put(ACT, closeDiscussionActivity(activity));
                return subModel;
        }
        throw new IllegalStateException("activity type is illegal :" + activity.getActivityType());
    }

    private Map<String, Object> createTime(Timestamp createdTime) {
        Map<String, Object> subModel = new HashMap<>();
        subModel.put(TIME, createdTime.toString());
        return subModel;
    }

    private List<Map<String, String>> createDiscussionActivity(Activity activity) {
        List<Map<String, String>> fragments = new ArrayList<>();
        fragments.add(createUserName(activity.getUserId()));
        fragments.add(createDiscussionName(activity.getTargetDiscussionId()));
        fragments.add(createCreateDiscussion());
        return fragments;
    }

    private Map<String, String> createUserName(long userId) {
        Map<String, String> subModel = new HashMap<>();
        User user = userService.findUserById(userId);
        subModel.put(PREFIX, "activity.user.name.prefix");
        subModel.put(STRING, (user == null ? "anonymous" : user.getName()));
        subModel.put(SUFFIX, "activity.user.name.suffix");
        return subModel;
    }

    private Map<String, String> createDiscussionName(Long discussionId) {
        Map<String, String> subModel = new HashMap<>();
        Discussion discussion = discussionDao.findDiscussionById(discussionId);
        String subject = (discussion == null ? "deleted" : discussion.getSubject());
        subModel.put(PREFIX, "activity.discussion.title.prefix");
        subModel.put(STRING, subject);
        subModel.put(SUFFIX, "activity.discussion.title.suffix");
        return subModel;
    }

    private Map<String, String> createCreateDiscussion() {
        Map<String, String> subModel = new HashMap<>();
        subModel.put(PREFIX, "activity.discussion.create.prefix");
        subModel.put(STRING, null);
        subModel.put(SUFFIX, "activity.discussion.create.suffix");
        return subModel;
    }

    private List<Map<String, String>> solveDiscussionActivity(Activity activity) {
        List<Map<String, String>> fragments = new ArrayList<>();
        fragments.add(createUserName(activity.getUserId()));
        fragments.add(createDiscussionName(activity.getTargetDiscussionId()));
        fragments.add(createSolveDiscussion());
        return fragments;
    }

    private Map<String, String> createSolveDiscussion() {
        Map<String, String> subModel = new HashMap<>();
        subModel.put(PREFIX, "activity.discussion.solve.prefix");
        subModel.put(STRING, null);
        subModel.put(SUFFIX, "activity.discussion.solve.suffix");
        return subModel;
    }

    private List<Map<String, String>> closeDiscussionActivity(Activity activity) {
        List<Map<String, String>> fragments = new ArrayList<>();
        fragments.add(createUserName(activity.getUserId()));
        fragments.add(createDiscussionName(activity.getTargetDiscussionId()));
        fragments.add(createCloseDiscussion());
        return fragments;
    }

    private Map<String, String> createCloseDiscussion() {
        Map<String, String> subModel = new HashMap<>();
        subModel.put(PREFIX, "activity.discussion.close.prefix");
        subModel.put(STRING, null);
        subModel.put(SUFFIX, "activity.discussion.close.suffix");
        return subModel;
    }
}
