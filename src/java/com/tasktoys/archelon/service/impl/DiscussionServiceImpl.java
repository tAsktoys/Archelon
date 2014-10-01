/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.CategoryDao;
import com.tasktoys.archelon.data.dao.DiscussionContentDao;
import com.tasktoys.archelon.data.dao.DiscussionDao;
import com.tasktoys.archelon.data.dao.UserDao;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.service.DiscussionService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YuichiroSato
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionDao discussionDao;
    @Autowired
    private DiscussionContentDao discussionContentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;

    static final Logger log = Logger.getLogger(DiscussionServiceImpl.class.getName());

    @Override
    public int countDiscussion() {
        return discussionDao.countDiscussions();
    }

    @Override
    public int countDiscussionByMainCategory(int mainId) {
        return discussionDao.countDiscussionsByCategoryList(categoryDao.findSubCategories(mainId));
    }

    @Override
    public int countDiscussionBySubCategory(int subId) {
        return discussionDao.countDiscussionsByCategoryId(subId);
    }

    private List<Discussion> getNewestDiscussionList(int n) {
        return discussionDao.findNewestDiscussionList(n);
    }

    private List<Discussion> getNewestDiscussionListWithOffset(int n, int offset) {
        return discussionDao.findNewestDiscussionListWithOffset(n, offset);
    }

    private List<Discussion> getNewestDiscussionListByMainCategory(int n, int mainId) {
        return discussionDao.findNewestDiscussionListByCategoryList(categoryDao.findSubCategories(mainId), n, 0);
    }

    private List<Discussion> getNewestDiscussionListByMainCategoryWithOffset(int n, int mainId, int offset) {
        return discussionDao.findNewestDiscussionListByCategoryList(categoryDao.findSubCategories(mainId), n, offset);
    }

    private List<Discussion> getNewestDiscussionListBySubCategory(int n, int categoryId) {
        return discussionDao.findNewestDiscussionListByCategoryId(n, categoryId, 0);
    }

    private List<Discussion> getNewestDiscussionListBySubCategoryWithOffset(int n, int categoryId, int offset) {
        return discussionDao.findNewestDiscussionListByCategoryId(n, categoryId, offset);
    }
    
    @Override
    public void updateUpdateTime(long discussionId) {
        long unixtime = System.currentTimeMillis();
        discussionDao.updateUpdateTime(discussionId, new Timestamp(unixtime));
    }
    
    @Override
    public void incrementParticipants(long discussionId) {
        discussionDao.incrementParticipants(discussionId);
    }
    
    @Override
    public void incrementPosts(long discussionId) {
        discussionDao.incrementPosts(discussionId);
    }

    @Override
    public void insertDiscussion(Discussion discussion, DiscussionContent content) {
        discussionDao.insertDiscussion(discussion);
        List<Discussion> discussionList = discussionDao.findNewestDiscussionList(10); // TODO: replace by findDiscussionByAuthor
        long authorId = content.getFirstAuthorId();
        for (Discussion d : discussionList) {
            if (d.getAuthorID() == authorId) {
                content.setDiscussionId(d.getID());
                discussionContentDao.insert(content);
            }
        }
    }

    @Override
    public List<Map<String, String>> replaceAuthorIDToAuthorName(List<Discussion> dls) {
        List<Map<String, String>> mls = new ArrayList<>();
        for (Discussion d : dls) {
            String author_name = userDao.findUserByID(d.getAuthorID()).getName();
            mls.add(d.replaceAuthorIDToAuthorName(author_name));
        }
        return mls;
    }

    @Override
    public Map<String, List<Map<String, String>>> createLatestDiscussionList(String name, int n) {
        return Collections.singletonMap(name,
                replaceAuthorIDToAuthorName(discussionDao.findNewestDiscussionList(n)));
    }

    @Override
    public Map<String, List<Map<String, String>>> createLatestDiscussionListByMainCategory(String name, int n, int mainId) {
        return Collections.singletonMap(name,
                replaceAuthorIDToAuthorName(
                        getNewestDiscussionListByMainCategory(n, mainId)));
    }

    @Override
    public Map<String, List<Map<String, String>>> createLatestDiscussionListBySubCategory(String name, int n, int subId) {
        return Collections.singletonMap(name,
                replaceAuthorIDToAuthorName(
                        getNewestDiscussionListBySubCategory(n, subId)));
    }

    @Override
    public Map<String, List<Map<String, String>>> createDiscussionList(String name, int pageNumber, int pageSize) {
        int offset = calculateOffset(pageNumber, pageSize);
        return Collections.singletonMap(name,
                replaceAuthorIDToAuthorName(getNewestDiscussionListWithOffset(pageSize, offset)));
    }

    @Override
    public Map<String, List<Map<String, String>>> createDiscussionListByMainCategory(String name, int pageNumber, int pageSize, int mainId) {
        int offset = calculateOffset(pageNumber, pageSize);
        return Collections.singletonMap(name,
                replaceAuthorIDToAuthorName(getNewestDiscussionListByMainCategoryWithOffset(pageSize, mainId, offset)));
    }

    @Override
    public Map<String, List<Map<String, String>>> createDiscussionListBySubCategory(String name, int pageNumber, int pageSize, int subId) {
        int offset = calculateOffset(pageNumber, pageSize);
        return Collections.singletonMap(name,
                replaceAuthorIDToAuthorName(getNewestDiscussionListBySubCategoryWithOffset(pageSize, subId, offset)));
    }
    
    private int calculateOffset(int pageNumber, int pageSize) {
        return pageSize * (pageNumber - 1);
    }
}
