/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author YuichiroSato
 */
public interface DiscussionService {

    public Map<String, List<Map<String, String>>> createLatestDiscussionList(String name, int n);

    public Map<String, List<Map<String, String>>> createLatestDiscussionListByMainCategory(String name, int n, int mainId);

    public Map<String, List<Map<String, String>>> createLatestDiscussionListBySubCategory(String name, int n, int subId);

    public Map<String, List<Map<String, String>>> createDiscussionList(String name, int pageNumber, int pageSize);

    public Map<String, List<Map<String, String>>> createDiscussionListByMainCategory(String name, int pageNumber, int pageSize, int mainId);

    public Map<String, List<Map<String, String>>> createDiscussionListBySubCategory(String name, int pageNumber, int pageSize, int subId);

    public Map<String, Map<String, Object>> createDiscussionLink(String name, int listSize, int currentPageNumber);

    public Map<String, Map<String, Object>> createDiscussionLinkByMainCategory(String name, int listSize, int currentPageNumber, int mainId);

    public Map<String, Map<String, Object>> createDiscussionLinkBySubCategory(String name, int listSize, int currentPageNumber, int subId);

    public void saveNewDiscussion(String subject, User author, int categoryId, String discription);

    public void updateDiscussionProperties(long discussionId, User user);
}
