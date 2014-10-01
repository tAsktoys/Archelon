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
//    public List<Discussion> getNewestDiscussionList(int n);
//    public List<Discussion> getNewestDiscussionListWithOffset(int n, int offset);
//    public List<Discussion> getNewestDiscussionListByMainCategory(int n, int mainId);
//    public List<Discussion> getNewestDiscussionListByMainCategoryWithOffset(int n, int mainId, int offset);
//    public List<Discussion> getNewestDiscussionListBySubCategory(int n, int subId);
//    public List<Discussion> getNewestDiscussionListBySubCategoryWithOffset(int n, int subId, int offset);
    public void insertDiscussion(Discussion discussion, DiscussionContent content);
    public void updateUpdateTime(long discussionId);
    public void incrementParticipants(long discussionId);
    public void incrementPosts(long discussionId);
    
    public Map<String, List<Map<String, String>>> createLatestDiscussionList(String name, int n);
    public Map<String, List<Map<String, String>>> createLatestDiscussionListByMainCategory(String name, int n, int mainId);
    public Map<String, List<Map<String, String>>> createLatestDiscussionListBySubCategory(String name, int n, int subId);
    
    public Map<String, List<Map<String, String>>> createDiscussionList(String name, int pageNumber, int pageSize);
    public Map<String, List<Map<String, String>>> createDiscussionListByMainCategory(String name, int pageNumber, int pageSize, int mainId);
    public Map<String, List<Map<String, String>>> createDiscussionListBySubCategory(String name, int pageNumber, int pageSize, int subId);
    
    /** 
     * Replace <code>long</code> author id to <code>String</code> author name in discussions
     * 
     * @param dls list of discussions
     * @return list of discussions converted to maps with replacing author id to author name 
     */
    public List<Map<String, String>> replaceAuthorIDToAuthorName(List<Discussion> dls);
}
