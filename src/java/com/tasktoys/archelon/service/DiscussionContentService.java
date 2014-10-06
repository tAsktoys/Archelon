/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.data.entity.DiscussionContent.Post;
import com.tasktoys.archelon.data.entity.User;

/**
 * Interface definision of discussion content interactions.
 *
 * @author YuichiroSato
 * @author mikan
 * @version 0.1
 */
public interface DiscussionContentService {
    
    public DiscussionContent getDiscussionContent(long discussionId);
    public Post getLastPost(long discussionId);
    public void insertPost(long discussionId, Post post, User author);
}
