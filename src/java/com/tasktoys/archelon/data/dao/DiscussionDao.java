/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

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
    
    public int countDiscussion();
    public int countDiscussionByCategoryId(int categoryId);
    public int countDiscussionByCategoryIdList(List<Integer> categoryIdList);
    
    /**
     * Find Newest Discussions.
     * 
     * @param n number of discussions to return
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionList(int n);
    
    public List<Discussion> findNewestDiscussionListWithOffset(int n, int offset);
    
    /**
     * Find Newest Discussions for each main category.
     * 
     * @param n number of discussions to return
     * @param main_id id of queried main category
     * @return list of discussion, or empty if not found.
     */
    //public List<Discussion> findNewestDiscussionListByMainCategory(int n, int main_id);
    
    /**
     * Find Newest Discussions for each main and sub category.
     * 
     * @param n number of discussions to return
     * @param main_id id of queried main category
     * @param sub_id if of queried sub category
     * @return list of discussion, or empty if not found.
     */
    //public List<Discussion> findNewestDiscussionListBySubCategory(int n, int main_id, int sub_id);
    
    /**
     * Insert new discussion to database.
     * @param discussion <code>Discussion</code> to insert
     */
    public void insertDiscussion(Discussion discussion);
    
    public List<Discussion> findNewestDiscussionListByCategoryId(int categoryId, int n, int offset);
    public List<Discussion> findNewestDiscussionListByCategoryIdList(List<Integer> categoryId, int n, int offset);
}
