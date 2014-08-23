/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.repository;

import com.tasktoys.archelon.data.entity.DiscussionContent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author mikan
 */
public interface DiscussionContentRepository extends MongoRepository<DiscussionContent, Long> {
    
    public DiscussionContent findDiscussionContentById(long id);
    
    public void insertContent(DiscussionContent content);
    
    public void addPost(DiscussionContent content);
}
