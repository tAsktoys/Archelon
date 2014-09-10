/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import java.util.List;
import java.util.Map;

/**
 *
 * @author YuichiroSato
 */
public interface DiscussionService {
    
    public int countDiscussion();
    public int countDiscussionByMainCategory(int mainId);
    public int countDiscussionBySubCategory(int subId);
    public List<Discussion> getNewestDiscussionList(int n);
    public List<Discussion> getNewestDiscussionListWithOffset(int n, int offset);
    public List<Discussion> getNewestDiscussionListByMainCategory(int n, int mainId);
    public List<Discussion> getNewestDiscussionListByMainCategoryWithOffset(int n, int mainId, int offset);
    public List<Discussion> getNewestDiscussionListBySubCategory(int n, int subId);
    public List<Discussion> getNewestDiscussionListBySubCategoryWithOffset(int n, int subId, int offset);
    public void insertDiscussion(Discussion discussion, DiscussionContent content);
    
    /** Replace <code>long</code> author id to <code>String</code> author name in discussions
     * 
     * @param dls list of discussions
     * @return list of discussions converted to maps with replacing author id to author name 
     */
    public List<Map<String, String>> replaceAuthorIDToAuthorName(List<Discussion> dls);
}
