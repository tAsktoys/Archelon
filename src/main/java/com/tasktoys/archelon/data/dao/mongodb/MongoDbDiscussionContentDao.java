/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.mongodb;

import com.mongodb.WriteResult;
import com.tasktoys.archelon.data.dao.DiscussionContentDao;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mikan
 */
@Repository
public class MongoDbDiscussionContentDao implements DiscussionContentDao {

    @Autowired
    MongoTemplate mongoTemplate;

    Logger log = Logger.getLogger(MongoDbDiscussionContentDao.class.getName());

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
    public void update(DiscussionContent.Post post) {
        // TODO: implement thread-safe appending.
    }

    @Override
    public void insertPost(long discussionId, DiscussionContent.Post post) {
        Query query = new Query(Criteria.where("discussionId").is(discussionId));
        WriteResult wr = mongoTemplate.updateFirst(query,
                new Update().addToSet("posts", post),
                DiscussionContent.class);
        log.log(Level.INFO, wr.toString());
        log.log(Level.INFO, Boolean.toString(wr.isUpdateOfExisting()));
    }

    @Override
    public void insertParticipants(long discussionId, long userId) {
        Query query = new Query(Criteria.where("discussionId").is(discussionId));
        mongoTemplate.updateFirst(query,
                new Update().addToSet("participants", userId),
                DiscussionContent.class);
    }
}
