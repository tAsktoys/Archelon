/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.mongodb.MongoDbDiscussionContentDao;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.data.entity.DiscussionContent.Post;
import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.DiscussionContentService;
import com.tasktoys.archelon.service.DiscussionService;
import java.util.List;
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
    private DiscussionService discussionService;
    @Autowired
    private MongoDbDiscussionContentDao mongoDbDiscussionContentDao;
    
    @Override
    public DiscussionContent getDiscussionContent(long discussionId) {
        return mongoDbDiscussionContentDao.findByDiscussionId(discussionId);
    }
    
    @Override
    public Post getLastPost(long discussionId) {
        List<Post> posts = mongoDbDiscussionContentDao.findByDiscussionId(discussionId).getPosts();
        return posts.get(posts.size() - 1);
    }
    
    @Override
    public void insertPost(long discussionId, Post post, User author) {
        if (author != null) {
            post.setAuthorId(author.getId());
        }
        Post lastPost = getLastPost(discussionId);
        if (!post.equals(lastPost)) {
            mongoDbDiscussionContentDao.insertPost(discussionId, post);
            discussionService.updateDiscussionProperties(discussionId, author);
        }

    }
}
