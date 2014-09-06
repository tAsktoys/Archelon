/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.DiscussionDao;
import com.tasktoys.archelon.data.entity.Discussion;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YuichiroSato
 */
@Repository
public class JdbcDiscussionDao implements DiscussionDao {

    private JdbcTemplate jdbcTemplate;
    private static final String TABLE_NAME = "discussion";

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
     * This is columns in database.
     * Each value is ordred by the same order as in the database.
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
    public int countDiscussion() {
        String sql = "select count(*) from " + TABLE_NAME;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public int countDiscussionByCategoryId(int categoryId) {
        String sql = "select count(*) from " + TABLE_NAME
                + " where " + Column.CATEGORY_ID.toString() + " = " + categoryId;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public int countDiscussionByCategoryIdList(List<Integer> categoryIdList) {
        String sql = "select count(*) from " + TABLE_NAME
                + " where " ;
        for (int i : categoryIdList) {
            sql += Column.CATEGORY_ID.toString() + " = " + i + " or ";
        }
        sql = sql.substring(0, sql.length() - 4);
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

//    @Override
//    public List<Discussion> findNewestDiscussionListByMainCategory(int n, int main_id) {
//        String sql = "select * from " + TABLE_NAME
//                + " where " + Column.CATEGORY_ID.toString() + " = " + main_id
//                + " order by " + Column.CREATE_TIME.toString() + " desc"
//                + " limit " + n
//                + " offset " + offset + ";";
//        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
//    }
//
//    @Override
//    public List<Discussion> findNewestDiscussionListBySubCategory(int n, int main_id, int sub_id) {
//        String sql = "select * from " + TABLE_NAME
//                + " where " + Column.CATEGORY_ID.toString() + " = " + main_id
//                + " order by " + Column.CREATE_TIME.toString() + " desc"
//                + " limit " + n + ";";
//        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
//    }
    
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
    public List<Discussion> findNewestDiscussionListByCategoryIdList(List<Integer> categoryIdList, int n, int offset) {
        String sql = "select * from " + TABLE_NAME
                + " where ";
        for (int i : categoryIdList) {
            sql += Column.CATEGORY_ID.toString() + " = " + i + " or ";
        }
        sql = sql.substring(0, sql.length() - 4);
                sql += " order by " + Column.CREATE_TIME.toString() + " desc"
                + " limit " + n
                + " offset " + offset + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }

    @Override
    public void insertDiscussion(Discussion discussion) {
        String sql = "insert into " + TABLE_NAME + encodeColumnToSet();
        jdbcTemplate.update(sql, discussion.toObject());
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
        for(Column c : Column.values()) {
            sql += c.toString() + "=?," ;
        }
        return sql.substring(0, sql.length() - 1);
    }
}
