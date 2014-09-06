/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Discussion;
import java.util.List;

/**
 * Interface of discussion data operations.
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.1
 */
public interface DiscussionDao {
    
    public int countDiscussions();
    public int countDiscussionsByCategoryId(int categoryId);
    public int countDiscussionsByCategoryList(List<Category> categoryList);
    
    /**
     * Find Newest Discussions.
     * 
     * @param n number of discussions to return
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionList(int n);
    
    public List<Discussion> findNewestDiscussionListWithOffset(int n, int offset);
    
    /**
     * Insert new discussion to database.
     * @param discussion <code>Discussion</code> to insert
     */
    public void insertDiscussion(Discussion discussion);
    
    public List<Discussion> findNewestDiscussionListByCategoryId(int categoryId, int n, int offset);
    public List<Discussion> findNewestDiscussionListByCategoryList(List<Category> category, int n, int offset);
}
