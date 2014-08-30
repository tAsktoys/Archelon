/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public void updateDiscussionContent(DiscussionContent content) {
        
    }
}
