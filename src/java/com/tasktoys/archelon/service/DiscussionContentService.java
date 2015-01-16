/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.DiscussionContent.Post;
import com.tasktoys.archelon.data.entity.User;
import java.util.Locale;
import java.util.Map;

/**
 * Interface definision of discussion content interactions.
 *
 * @author YuichiroSato
 * @author mikan
 * @version 0.1
 */
public interface DiscussionContentService {
    
    public Map<String, Map<String, Object>> createDiscussionContent(String name, long discussionId, User user, Locale locale);
    public void insertPost(long discussionId, Post post, User author);
}
