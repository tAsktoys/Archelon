/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.mongodb;

import com.tasktoys.archelon.data.dao.DiscussionContentDao;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mikan
 */
@Repository
public class MongoDbDiscussionContentDao implements DiscussionContentDao {
    
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public DiscussionContent findByDiscussionId(long id) {
        Query query = new Query(Criteria.where("discussionId").is(id));
        return mongoTemplate.findOne(query, DiscussionContent.class);
    }

    @Override
    public void insert(DiscussionContent content) {
        mongoTemplate.insert(content);
    }

    @Override
    public void update(DiscussionContent content) {
        // TODO: implement thread-safe appending.
    }
    
    
}
