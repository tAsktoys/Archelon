/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Discussion;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface of discussion data operations.
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.1
 */
public interface DiscussionDao {
    
    /**
     * Count up all descussions.
     * @return number of discussion.
     */
    public int countDiscussions();
    
    /**
     * Count up discussions which are in a category.
     * @param categoryId
     * @return number of discussions which are specified by a category id
     */
    public int countDiscussionsByCategoryId(int categoryId);
    
    /**
     * Count up all of discussions which are in categories.
     * @param categoryList
     * @return number of discussions which are specified by category ids.
     */
    public int countDiscussionsByCategoryList(List<Category> categoryList);
    
    /**
     * Find Newest Discussions.
     * 
     * @param n number of discussions to return
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionList(int n);
    
    /**
     * Find Newest Discussions with offset.
     * @param n number of discussions to return
     * @param offset number of discussions to omit
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionListWithOffset(int n, int offset);
    
    /**
     * Find newest discussions in a category.
     * @param n number of discussions to return
     * @param categoryId category id to specify discussions
     * @param offset number of discussions to omit
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionListByCategoryId(int n, int categoryId, int offset);
    
    /**
     * Find newest discussions in categories.
     * @param categoryList list of categories to specify discussions
     * @param n number of discussions to return
     * @param offset number of discussions to omit
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionListByCategoryList(List<Category> categoryList, int n, int offset);

    /**
     *
     * @param discussionId
     * @param updateTime
     */
    public void updateUpdateTime(long discussionId, Timestamp updateTime);

    /**
     *
     * @param discussionId
     */
    public void incrementParticipants(long discussionId);

    /**
     *
     * @param discussionId
     */
    public void incrementPosts(long discussionId);
    
    /**
     * Insert new discussion to database.
     * @param discussion <code>Discussion</code> to insert
     */
    public void insertDiscussion(Discussion discussion);
}
