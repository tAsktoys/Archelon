/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.DiscussionContent;

/**
 * Interface definision of discussion content interactions.
 *
 * @author YuichiroSato
 * @author mikan
 * @version 0.1
 */
public interface DiscussionContentService {
    
    public DiscussionContent getDiscussionContent(long discussionId);
    public void insertPost(long discussionId, DiscussionContent.Post post);
}
