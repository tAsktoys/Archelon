/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.DiscussionDao;
import com.tasktoys.archelon.data.entity.Discussion;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
        List<Discussion> list = new ArrayList<>();
        Discussion.Builder builder = new Discussion.Builder();
        builder.id((long)1);
        builder.author_id((long)2);
        builder.state(1);
        builder.create_time(new Timestamp(1));
        builder.update_time(new Timestamp(2));
        builder.subject("newest");
        builder.participants(1);
        builder.participants(1);
        list.add(builder.build());
        list.add(builder.build());
        return list;
    }

    @Override
    public List<Discussion> findDiscussionListBefor(BigInteger id, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Discussion> findNewestDiscussionListByMainCategory(int n, int main_id) {
        List<Discussion> list = new ArrayList<>();
        Discussion.Builder builder = new Discussion.Builder();
        builder.id((long)1);
        builder.author_id((long)2);
        builder.state(1);
        builder.create_time(new Timestamp(1));
        builder.update_time(new Timestamp(2));
        builder.subject("main_id is " + main_id);
        builder.participants(1);
        builder.participants(1);
        list.add(builder.build());
        list.add(builder.build());
        return list;
    }

    @Override
    public List<Discussion> findDiscussionListWithMainCategoryBefor(BigInteger id, int n, int main_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Discussion> findNewestDiscussionListBySubCategory(int n, int main_id, int sub_id) {
        List<Discussion> list = new ArrayList<>();
        Discussion.Builder builder = new Discussion.Builder();
        builder.id((long)1);
        builder.author_id((long)2);
        builder.state(1);
        builder.create_time(new Timestamp(1));
        builder.update_time(new Timestamp(2));
        builder.subject("main_id is " + main_id + " sub_id is " + sub_id);
        builder.participants(1);
        builder.participants(1);
        list.add(builder.build());
        list.add(builder.build());
        return list;
    }

    @Override
    public List<Discussion> findDiscussionListWithSubCategoryBefor(BigInteger id, int n, int main_id, int sub_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
