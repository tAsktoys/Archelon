/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.DiscussionContent;

/**
 *
 * @author mikan
 */
public interface DiscussionContentDao {
    
    /**
     * Find discussion by discussion id.
     * 
     * @param id discussion id
     * @return discussion content
     */
    public DiscussionContent findByDiscussionId(long id);
    
    /**
     * Insert new discussion.
     * 
     * @param content discussion content
     */
    public void insert(DiscussionContent content);
    
    /**
     * Insert last added post for the provided discussion thread.
     * 
     * @param post a post of the discussion content
     */
    public void update(DiscussionContent.Post post);
    
    /**
     *
     * @param discussionId
     * @param post
     */
    public void insertPost(long discussionId, DiscussionContent.Post post);
    
    /**
     *
     * @param discussionId
     * @param userId
     */
    public void insertParticipants(long discussionId, long userId);

}
