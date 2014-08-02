/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Discussion;
import java.math.BigInteger;
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
     * Find Newest Discussions.
     * 
     * @param n number of discussions to return
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionList(int n);
    
    /**
     * Find Discussions which created before a discussion made.
     * 
     * @param id id of discussion as an origin
     * @param n number of discussions to return
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findDiscussionListBefor(BigInteger id, int n);
    
    /**
     * Find Newest Discussions for each main category.
     * 
     * @param n number of discussions to return
     * @param main_id id of queried main category
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionListByMainCategory(int n, int main_id);
    
    /**
     * Find Discussions which created before a discussion made with main category.
     * 
     * @param id id of discussion as an origin
     * @param n number of discussions to return
     * @param main_id id of queried main category
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findDiscussionListWithMainCategoryBefor(BigInteger id, int n, int main_id);
    
    /**
     * Find Newest Discussions for each main and sub category.
     * 
     * @param n number of discussions to return
     * @param main_id id of queried main category
     * @param sub_id if of queried sub category
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findNewestDiscussionListBySubCategory(int n, int main_id, int sub_id);
    
    /**
     * Find Discussions which created before a discussion made with main and sub category.
     * 
     * @param id id of discussion as an origin
     * @param n number of discussions to return
     * @param main_id id of queried main category
     * @param sub_id if of queried sub category
     * @return list of discussion, or empty if not found.
     */
    public List<Discussion> findDiscussionListWithSubCategoryBefor(BigInteger id, int n, int main_id, int sub_id);
}
