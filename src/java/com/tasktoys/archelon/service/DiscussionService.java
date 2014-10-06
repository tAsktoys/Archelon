/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.data.entity.User;
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
    public void updateUpdateTime(long discussionId);
    public void incrementParticipants(long discussionId);
    public void incrementPosts(long discussionId);
    
    public Map<String, List<Map<String, String>>> createLatestDiscussionList(String name, int n);
    public Map<String, List<Map<String, String>>> createLatestDiscussionListByMainCategory(String name, int n, int mainId);
    public Map<String, List<Map<String, String>>> createLatestDiscussionListBySubCategory(String name, int n, int subId);
    
    public Map<String, List<Map<String, String>>> createDiscussionList(String name, int pageNumber, int pageSize);
    public Map<String, List<Map<String, String>>> createDiscussionListByMainCategory(String name, int pageNumber, int pageSize, int mainId);
    public Map<String, List<Map<String, String>>> createDiscussionListBySubCategory(String name, int pageNumber, int pageSize, int subId);
    
    public void saveNewDiscussion(String subject, User author, int categoryId, String discription);

    public void updateDiscussionProperties(long discussionId, User user);
}
