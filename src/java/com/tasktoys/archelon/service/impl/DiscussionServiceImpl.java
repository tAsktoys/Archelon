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
import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.ActivityService;
import com.tasktoys.archelon.service.DiscussionService;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 *
 * @author YuichiroSato
 * @since 0.1
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private DiscussionDao discussionDao;
    @Autowired
    private DiscussionContentDao discussionContentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;

    static final Logger log = Logger.getLogger(DiscussionServiceImpl.class.getName());

    private static final String PAGE_NUMBER_LIST = "pageNumberList";
    private static final String PREVIOUS = "previous";
    private static final String CURRENT = "current";
    private static final String NEXT = "next";
    private static final String END = "end";

    @Override
    public Map<String, List<Map<String, String>>> createLatestDiscussionList(String name, int n) {
        return Collections.singletonMap(name,
                replaceAuthorIDToAuthorName(discussionDao.findNewestDiscussionList(n)));
    }

    /**
     * Replace <code>long</code> author id to <code>String</code> author name in
     * discussions
     *
     * @param discussionList list of discussions
     * @return list of discussions converted to maps with replacing author id to
     * author name
     */
    private List<Map<String, String>> replaceAuthorIDToAuthorName(List<Discussion> discussionList) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Discussion discussion : discussionList) {
            String authorName = userDao.findUserByID(discussion.getAuthorId()).getName();
            mapList.add(replacedMap(discussion, authorName));
        }
        return mapList;
    }
    
    private Map<String, String> replacedMap(Discussion discussion, String authorName) {
        Map<String, String> map = discussion.toMap();
        map.remove("authorId");
        map.put("authorId", authorName);
        return map;
    }
    
    @Override
    public Map<String, List<Map<String, String>>> createLatestDiscussionListByMainCategory(String name, int n, int mainId) {
        return Collections.singletonMap(name, latestDiscussionsByMain(n, mainId));
    }

    private List<Map<String, String>> latestDiscussionsByMain(int n, int mainId) {
        return replaceAuthorIDToAuthorName(
                discussionDao.findNewestDiscussionListByCategoryList(
                        categoryDao.findSubCategories(mainId), n, 0));
    }

    @Override
    public Map<String, List<Map<String, String>>> createLatestDiscussionListBySubCategory(String name, int n, int subId) {
        return Collections.singletonMap(name, latestDiscussionsBySub(n, subId));
    }

    private List<Map<String, String>> latestDiscussionsBySub(int n, int categoryId) {
        return replaceAuthorIDToAuthorName(
                discussionDao.findNewestDiscussionListByCategoryId(n, categoryId, 0));
    }

    @Override
    public Map<String, List<Map<String, String>>> createDiscussionList(String name, int pageNumber, int pageSize) {
        int offset = calculateOffset(pageNumber, pageSize);
        return Collections.singletonMap(name, latestDiscussionsWithOffset(pageSize, offset));
    }

    private int calculateOffset(int pageNumber, int pageSize) {
        return pageSize * (pageNumber - 1);
    }

    private List<Map<String, String>> latestDiscussionsWithOffset(int n, int offset) {
        return replaceAuthorIDToAuthorName(
                discussionDao.findNewestDiscussionListWithOffset(n, offset));
    }

    @Override
    public Map<String, List<Map<String, String>>> createDiscussionListByMainCategory(String name, int pageNumber, int pageSize, int mainId) {
        int offset = calculateOffset(pageNumber, pageSize);
        return Collections.singletonMap(name,
                latestDiscussionsMainOffset(pageSize, mainId, offset));
    }

    private List<Map<String, String>> latestDiscussionsMainOffset(int n, int mainId, int offset) {
        return replaceAuthorIDToAuthorName(
                discussionDao.findNewestDiscussionListByCategoryList(
                        categoryDao.findSubCategories(mainId), n, offset));
    }

    @Override
    public Map<String, List<Map<String, String>>> createDiscussionListBySubCategory(String name, int pageNumber, int pageSize, int subId) {
        int offset = calculateOffset(pageNumber, pageSize);
        return Collections.singletonMap(name, latestDiscussionsSubOffset(pageSize, subId, offset));
    }

    private List<Map<String, String>> latestDiscussionsSubOffset(int n, int categoryId, int offset) {
        return replaceAuthorIDToAuthorName(
                discussionDao.findNewestDiscussionListByCategoryId(n, categoryId, offset));
    }

    @Override
    public void saveNewDiscussion(String subject, User author, int categoryId, String discription) {
        long authorId = author.getId();
        Discussion discussion = makeNewDiscussion(subject, authorId, categoryId);
        DiscussionContent content = makeNewDiscussionContent(subject, authorId, discription);

        discussionDao.insertDiscussion(discussion);
        insertDiscussionContent(content, authorId);
        activityService.discussionMadeBy(author);
    }

    private Discussion makeNewDiscussion(String subject, long authorId, int categoryId) {
        Discussion.Builder builder = new Discussion.Builder();
        builder.subject(subject);
        builder.categoryId(categoryId);
        builder.authorId(authorId);
        return builder.buildForInsert();
    }

    private DiscussionContent makeNewDiscussionContent(String subject, long authorId, String discription) {
        DiscussionContent content = new DiscussionContent();
        content.setSubject(subject);
        content.addPost(authorId, discription);
        content.addParticipants(authorId);
        return content;
    }

    private void insertDiscussionContent(DiscussionContent content, long authorId) {
        List<Discussion> discussionList = discussionDao.findNewestDiscussionList(1); // TODO: replace by findDiscussionByAuthor
        for (Discussion d : discussionList) {
            if (d.getAuthorId() == authorId) {
                content.setDiscussionId(d.getId());
            }
        }

        try {
            discussionContentDao.insert(content);
        } catch (DuplicateKeyException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public void updateDiscussionProperties(long discussionId, User author) {
        incrementPosts(discussionId);
        updateUpdateTime(discussionId);

        List<Long> participateMember = discussionContentDao.findByDiscussionId(discussionId).getParticipateMember();
        if (author != null) {
            long userId = author.getId();
            if (!participateMember.contains(userId)) {
                discussionContentDao.insertParticipants(discussionId, userId);
                incrementParticipants(discussionId);
            }
        }
    }

    private void updateUpdateTime(long discussionId) {
        long unixtime = System.currentTimeMillis();
        discussionDao.updateUpdateTime(discussionId, new Timestamp(unixtime));
    }

    private void incrementParticipants(long discussionId) {
        discussionDao.incrementParticipants(discussionId);
    }

    private void incrementPosts(long discussionId) {
        discussionDao.incrementPosts(discussionId);
    }

    @Override
    public Map<String, Map<String, Object>> createDiscussionLink(String name,
            int listSize, int currentPageNumber) {
        int total = discussionDao.countDiscussions();
        int endPageNumber = calculateEndPageNumber(total, listSize);
        return createDiscussionLinkByEndPage(name, currentPageNumber, endPageNumber);
    }

    private int calculateEndPageNumber(int discussionNumber, int size) {
        return (int) Math.ceil((double) discussionNumber / size);
    }

    private Map<String, Map<String, Object>> createDiscussionLinkByEndPage(
            String name, int currentPageNumber, int endPageNumber) {
        Map<String, Object> subModel = createPageNumbers(endPageNumber);
        return Collections.singletonMap(name,
                setPageNumbers(subModel, currentPageNumber, endPageNumber));
    }

    private Map<String, Object> createPageNumbers(int endPageNumber) {
        Map<String, Object> subModel = new HashMap<>();
        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= endPageNumber; i++) {
            pageNumberList.add(i);
        }
        subModel.put(PAGE_NUMBER_LIST, pageNumberList);
        return subModel;
    }

    private Map<String, Object> setPageNumbers(Map<String, Object> subModel,
            int currentPageNumber, int endPageNumber) {
        int previousPageNumber = currentPageNumber - 1;
        int nextPageNumber = currentPageNumber + 1;
        if (currentPageNumber <= 1) {
            previousPageNumber = 1;
        }
        if (endPageNumber <= nextPageNumber) {
            nextPageNumber = endPageNumber;
        }
        subModel.put(PREVIOUS, previousPageNumber);
        subModel.put(CURRENT, currentPageNumber);
        subModel.put(NEXT, nextPageNumber);
        subModel.put(END, endPageNumber);
        return subModel;
    }

    @Override
    public Map<String, Map<String, Object>> createDiscussionLinkByMainCategory(
            String name, int listSize, int currentPageNumber, int mainId) {
        int endPageNumber = calculateEndPageNumber(countDiscussionByMainCategory(mainId), listSize);
        return createDiscussionLinkByEndPage(name, currentPageNumber, endPageNumber);
    }

    private int countDiscussionByMainCategory(int mainId) {
        return discussionDao.countDiscussionsByCategoryList(categoryDao.findSubCategories(mainId));
    }

    @Override
    public Map<String, Map<String, Object>> createDiscussionLinkBySubCategory(
            String name, int listSize, int currentPageNumber, int subId) {
        int total = discussionDao.countDiscussionsByCategoryId(subId);
        int endPageNumber = calculateEndPageNumber(total, listSize);
        return createDiscussionLinkByEndPage(name, currentPageNumber, endPageNumber);
    }
}
