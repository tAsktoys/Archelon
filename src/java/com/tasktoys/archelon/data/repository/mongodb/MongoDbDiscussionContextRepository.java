/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.repository.mongodb;

import com.tasktoys.archelon.data.repository.DiscussionContentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mikan
 */
@Repository
@Profile("mongodb")
public class MongoDbDiscussionContextRepository implements DiscussionContentRepository {
    
}
