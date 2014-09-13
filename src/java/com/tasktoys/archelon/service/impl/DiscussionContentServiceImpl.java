/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.mongodb.MongoDbDiscussionContentDao;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.service.DiscussionContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YuichiroSato
 * @author mikan
 * @version 0.1
 */
@Service
public class DiscussionContentServiceImpl implements DiscussionContentService {
    
    @Autowired
    private MongoDbDiscussionContentDao mongoDbDiscussionContentDao;
    
    @Override
    public DiscussionContent getDiscussionContent(long discussionId) {
        return mongoDbDiscussionContentDao.findByDiscussionId(discussionId);
    }
    
    @Override
    public void insertPost(long discussionId, DiscussionContent.Post post) {
        mongoDbDiscussionContentDao.insertPost(discussionId, post);
    }
    
    @Override
    public void insertParticipants(long discussionId, long usetId) {
        ;
    }
}
