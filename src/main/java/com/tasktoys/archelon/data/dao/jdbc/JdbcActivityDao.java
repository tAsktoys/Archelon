package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.ActivityDao;
import com.tasktoys.archelon.data.entity.Activity;
import com.tasktoys.archelon.data.entity.Activity.ActivityType;
import com.tasktoys.archelon.data.entity.Activity.Builder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    private JdbcTemplate jdbcTemplate;

    /**
     * Set data source. It invoke from Spring Framework.
     *
     * @param dataSource data source
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    static private final String TABLE_NAME = "activity";

    Logger log = Logger.getLogger(JdbcActivityDao.class.getName());

    /**
     * This is columns in database. Each value is ordred by the same order as in
     * the database.
     */
    private enum Column {

        ID, ACTIVITY_TYPE, USER_ID, CREATED_TIME, TARGET_DISCUSSION_ID,
        TARGET_USER_ID, TARGET_DISCUSSION_CONTENT_ID, TARGET_POST;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    @Override
    public List<Activity> findLatestActivities(int n) {
        String sql = "select * from " + TABLE_NAME
                + " order by " + " created_time " + " desc"
                + " limit " + n;
        return responseToActivityList(jdbcTemplate.queryForList(sql));
    }

    @Override
    public List<Activity> findLatestActivitiesByUserId(long userId, int n) {
        String sql = "select * from " + TABLE_NAME
                + " where " + "user_id=" + userId
                + " order by " + " created_time " + " desc"
                + " limit " + n;
        return responseToActivityList(jdbcTemplate.queryForList(sql));
    }

    @Override
    public void insertActivity(Activity activity) {
        String sql = "insert into " + TABLE_NAME + encodeColumnToSet();
        jdbcTemplate.update(sql, toObject(activity));
    }

    private List<Activity> responseToActivityList(List<Map<String, Object>> response) {
        List<Activity> activityList = new ArrayList<>();
        for (Map<String, Object> map : response) {
            Builder builder = new Builder();
            builder.id((long) map.get(Column.ID.toString()))
                    .activityType((int) map.get(Column.ACTIVITY_TYPE.toString()))
                    .userId((long) map.get(Column.USER_ID.toString()))
                    .createdTime((Timestamp) map.get(Column.CREATED_TIME.toString()))
                    .targetDiscussionId((Long) map.get(Column.TARGET_DISCUSSION_ID.toString()))
                    .targetUserId((Long) map.get(Column.TARGET_USER_ID.toString()))
                    .targetDiscussionConcentId((String) map.get(Column.TARGET_DISCUSSION_CONTENT_ID.toString()))
                    .targetPost((Integer) map.get(Column.TARGET_POST.toString()));
            activityList.add(builder.build());
        }
        return activityList;
    }

    private String encodeColumnToSet() {
        String sql = " set ";
        for (Column c : Column.values()) {
            sql += c.toString() + "=?,";
        }
        return sql.substring(0, sql.length() - ",".length());
    }

    private Object[] toObject(Activity activity) {
        return new Object[]{
            (activity.getId() == Activity.ILLEGAL_ID ? null : activity.getId()),
            activity.getActivityType().ordinal(),
            (activity.getUserId() == Activity.ILLEGAL_USER_ID ? null : activity.getUserId()),
            activity.getCreatedTime(), activity.getTargetDiscussionId(),
            activity.getTargetUserId(), activity.getTargetDiscussionContentId(),
            activity.getTargetPost()
        };
    }
}
