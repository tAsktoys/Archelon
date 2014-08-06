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
    
    public enum Column {
        id, author_id, category_id, state, create_time, update_time, subject,
        participants, posts
    }
    
    @Override
    public List<Discussion> findNewestDiscussionList(int n) {
        String sql = "select * from " + TABLE_NAME
                + " order by " + Column.create_time.name() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
//        List<Discussion> list = new ArrayList<>();
//        Discussion.Builder builder = new Discussion.Builder();
//        builder.id((long)1);
//        builder.author_id((long)2);
//        builder.state(1);
//        builder.create_time(new Timestamp(1));
//        builder.update_time(new Timestamp(2));
//        builder.subject("newest");
//        builder.participants(1);
//        builder.participants(1);
//        list.add(builder.build());
//        list.add(builder.build());
//        return list;
    }

    @Override
    public List<Discussion> findDiscussionListBefor(Long id, int n) {
        String sql = "select * from " + TABLE_NAME
                + "where " + Column.id + " > " + id.toString()
                + " order by " + Column.create_time.name() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }

    @Override
    public List<Discussion> findNewestDiscussionListByMainCategory(int n, int main_id) {
        String sql = "select * from " + TABLE_NAME
                + " where " + Column.category_id.name() + " = " + main_id
                + " order by " + Column.create_time.name() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
//        List<Discussion> list = new ArrayList<>();
//        Discussion.Builder builder = new Discussion.Builder();
//        builder.id((long)1);
//        builder.author_id((long)2);
//        builder.state(1);
//        builder.create_time(new Timestamp(1));
//        builder.update_time(new Timestamp(2));
//        builder.subject("main_id is " + main_id);
//        builder.participants(1);
//        builder.participants(1);
//        list.add(builder.build());
//        list.add(builder.build());
//        return list;
    }

    @Override
    public List<Discussion> findDiscussionListWithMainCategoryBefor(Long id, int n, int main_id) {
        String sql = "select * from " + TABLE_NAME
                + " where " + Column.category_id.name() + " = " + main_id
                + " and " + Column.id + " > " + id.toString()
                + " order by " + Column.create_time.name() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }

    @Override
    public List<Discussion> findNewestDiscussionListBySubCategory(int n, int main_id, int sub_id) {
        String sql = "select * from " + TABLE_NAME
                + " where " + Column.category_id.name() + " = " + main_id
                + " order by " + Column.create_time.name() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
//        List<Discussion> list = new ArrayList<>();
//        Discussion.Builder builder = new Discussion.Builder();
//        builder.id((long)1);
//        builder.author_id((long)2);
//        builder.state(1);
//        builder.create_time(new Timestamp(1));
//        builder.update_time(new Timestamp(2));
//        builder.subject("main_id is " + main_id + " sub_id is " + sub_id);
//        builder.participants(1);
//        builder.participants(1);
//        list.add(builder.build());
//        list.add(builder.build());
//        return list;
    }

    @Override
    public List<Discussion> findDiscussionListWithSubCategoryBefor(Long id, int n, int main_id, int sub_id) {
        String sql = "select * from " + TABLE_NAME
                + " where " + Column.category_id.name() + " = " + main_id
                + " and " + Column.id + " > " + id.toString()
                + " order by " + Column.create_time.name() + " desc"
                + " limit " + n + ";";
        return responseToDiscussionList(jdbcTemplate.queryForList(sql));
    }
    
    private List<Discussion> responseToDiscussionList(List<Map<String, Object>> response) {
        List<Discussion> dls = new ArrayList<>();
        for (Map<String, Object> row : response) {
            Discussion.Builder builder = new Discussion.Builder();
            builder.id((Long)row.get(Column.id.name()));
            builder.author_id((Long)row.get(Column.author_id.name()));
            builder.category_id((Integer)row.get(Column.category_id.name()));
            builder.state((Integer)row.get(Column.state.name()));
            builder.create_time((Timestamp)row.get(Column.create_time.name()));
            builder.update_time((Timestamp)row.get(Column.update_time.name()));
            builder.subject((String)row.get(Column.subject.name()));
            builder.participants((Integer)row.get(Column.participants.name()));
            builder.posts((Integer)row.get(Column.posts.name()));
            dls.add(builder.build());
        }
        return dls;
    }
}
