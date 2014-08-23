/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.DiscussionContentDao;
import com.tasktoys.archelon.data.dao.DiscussionDao;
import com.tasktoys.archelon.data.dao.UserDao;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.service.DiscussionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    
    @Override
    public List<Discussion> getNewestDiscussionList(int n) {
        return discussionDao.findNewestDiscussionList(n);
    }
    
    @Override
    public List<Discussion> getDiscussionListAfter(long id, int n) {
        return discussionDao.findDiscussionListAfter(id, n);
    }

    @Override
    public List<Discussion> getDiscussionListBefore(long id, int n) {
        return discussionDao.findDiscussionListBefore(id, n);
    }

    @Override
    public List<Discussion> getNewestDiscussionListByMainCategory(int n, int main_id) {
        return discussionDao.findNewestDiscussionListByMainCategory(n, main_id);
    }

    @Override
    public List<Discussion> getDiscussionListWithMainCategoryBefore(long id, int n, int main_id) {
        return discussionDao.findDiscussionListWithMainCategoryBefore(id, n, main_id);
    }

    @Override
    public List<Discussion> getNewestDiscussionListBySubCategory(int n, int main_id, int sub_id) {
        return discussionDao.findNewestDiscussionListBySubCategory(n, main_id, sub_id);
    }

    @Override
    public List<Discussion> getDiscussionListWithSubCategoryBefore(long id, int n, int main_id, int sub_id) {
        return discussionDao.findDiscussionListWithSubCategoryBefore(id, n, main_id, sub_id);
    }
    
    @Override
    public void insertDiscussion(Discussion discussion, DiscussionContent content) {
        discussionDao.insertDiscussion(discussion);
        discussionContentDao.insert(content);
        
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
}
