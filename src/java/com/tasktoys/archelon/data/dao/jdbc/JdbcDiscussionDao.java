/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.DiscussionDao;
import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.Discussion.Builder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YuichiroSato
 */
@Repository
public class JdbcDiscussionDao implements DiscussionDao {
    
    private JdbcTemplate jdbcTemplate;
    private static final String TABLE_NAME = "discussion";
    
    static final Logger log = Logger.getLogger(JdbcDiscussionDao.class.getName());

    /**
     * Set data source. It invoke from Spring Framework.
     *
     * @param dataSource data source
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * This is columns in database. Each value is ordred by the same order as in
     * the database.
     */
    public enum Column {
        
        ID, AUTHOR_ID, CATEGORY_ID, STATE, CREATE_TIME, UPDATE_TIME, SUBJECT,
        PARTICIPANTS, POSTS;
        
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    
    @Override
    public int countDiscussions() {
        String sql = "select count(*) from " + TABLE_NAME;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public int countDiscussionsByCategoryId(int categoryId) {
        String sql = "select count(*) from " + TABLE_NAME
                + " where " + Column.CATEGORY_ID.toString() + " = " + categoryId;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public int countDiscussionsByCategoryList(List<Category> categoryList) {
        String sql = "select count(*) from " + TABLE_NAME
                + encodeCategoryIdListToWhere(categoryList);
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public List<Discussion> findNewestDiscussionList(int n) {
        String sql = "select * from " + TABLE_NAME
                + " order by " + Column.CREATE_TIME.toString() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }
    
    @Override
    public List<Discussion> findNewestDiscussionListWithOffset(int n, int offset) {
        String sql = "select * from " + TABLE_NAME
                + " order by " + Column.CREATE_TIME.toString() + " desc"
                + " limit " + n
                + " offset " + offset + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }
    
    @Override
    public List<Discussion> findNewestDiscussionListByCategoryId(int n, int categoryId, int offset) {
        String sql = "select * from " + TABLE_NAME
                + " where " + Column.CATEGORY_ID.toString() + " = " + categoryId
                + " order by " + Column.CREATE_TIME.toString() + " desc"
                + " limit " + n
                + " offset " + offset + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }
    
    @Override
    public List<Discussion> findNewestDiscussionListByCategoryList(List<Category> categoryList, int n, int offset) {
        String sql = "select * from " + TABLE_NAME
                + encodeCategoryIdListToWhere(categoryList)
                + " order by " + Column.CREATE_TIME.toString() + " desc"
                + " limit " + n
                + " offset " + offset + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }
    
    @Override
    public Discussion findDiscussionById(long id) {
        String sql = "select * from " + TABLE_NAME + " where id=?";
        return jdbcTemplate.queryForObject(sql, new DiscussionRowMapper(), id);
    }
    
    @Override
    public List<Discussion> findLatestDiscussionsByAuthorId(long authorId, int n) {
        String sql = "select * from " + TABLE_NAME
                + " where " + Column.AUTHOR_ID.toString() + "=" + authorId
                + " order by " + Column.CREATE_TIME.toString() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }
    
    @Override
    public void insertDiscussion(Discussion discussion) {
        String sql = "insert into " + TABLE_NAME + encodeColumnToSet();
        jdbcTemplate.update(sql, discussion.toObject());
    }
    
    @Override
    public void updateUpdateTime(long discussionId, Timestamp updateTime) {
        String sql = "update " + TABLE_NAME + " set update_time=? where id=?";        
        jdbcTemplate.update(sql, updateTime, discussionId);
    }
    
    @Override
    public void incrementParticipants(long discussionId) {
        String sql = "update " + TABLE_NAME + " set participants=participants+1 where id=?";
        jdbcTemplate.update(sql, discussionId);
    }
    
    @Override
    public void incrementPosts(long discussionId) {
        String sql = "update " + TABLE_NAME + " set posts=posts+1 where id=?";
        jdbcTemplate.update(sql, discussionId);
    }
    
    private List<Discussion> responseToDiscussionList(List<Map<String, Object>> response) {
        List<Discussion> dls = new ArrayList<>();
        for (Map<String, Object> row : response) {
            Discussion.Builder builder = new Discussion.Builder();
            try {
                builder.id((long) row.get(Column.ID.toString()));
                builder.authorID((long) row.get(Column.AUTHOR_ID.toString()));
                builder.categoryID((int) row.get(Column.CATEGORY_ID.toString()));
                builder.state((int) row.get(Column.STATE.toString()));
                builder.createTime((Timestamp) row.get(Column.CREATE_TIME.toString()));
                builder.updateTime((Timestamp) row.get(Column.UPDATE_TIME.toString()));
                builder.subject((String) row.get(Column.SUBJECT.toString()));
                builder.participants((int) row.get(Column.PARTICIPANTS.toString()));
                builder.posts((int) row.get(Column.POSTS.toString()));
                dls.add(builder.build());
            } catch (IllegalStateException | NullPointerException e) {
                ;
            }
        }
        return dls;
    }
    
    private String encodeColumnToSet() {
        String sql = " set ";
        for (Column c : Column.values()) {
            sql += c.toString() + "=?,";
        }
        return sql.substring(0, sql.length() - ",".length());
    }
    
    private String encodeCategoryIdListToWhere(List<Category> categoryList) {
        if (categoryList == null || categoryList.isEmpty()) {
            return "";
        }
        
        String sql = " where " + Column.CATEGORY_ID.toString() + " in(";
        for (Category c : categoryList) {
            sql += c.getId() + ",";
        }
        sql = sql.substring(0, sql.length() - ",".length());
        return sql + ")";
    }
    
    private static class DiscussionRowMapper implements RowMapper<Discussion> {
        
        @Override
        public Discussion mapRow(ResultSet result, int row) throws SQLException {
            Builder builder = new Builder();
            builder.id(result.getLong(Column.ID.toString()));
            builder.authorID(result.getLong(Column.AUTHOR_ID.toString()));
            builder.categoryID(result.getInt(Column.CATEGORY_ID.toString()));
            builder.state(result.getInt(Column.STATE.toString()));
            builder.createTime(result.getTimestamp(Column.CREATE_TIME.toString()));
            builder.updateTime(result.getTimestamp(Column.UPDATE_TIME.toString()));
            builder.subject(result.getString(Column.SUBJECT.toString()));
            builder.participants(result.getInt(Column.PARTICIPANTS.toString()));
            builder.posts(result.getInt(Column.POSTS.toString()));
            return builder.build();
        }
        
    }
}
