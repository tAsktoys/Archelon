/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.DiscussionDao;
import com.tasktoys.archelon.data.dao.UserDao;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.service.DiscussionService;
import java.math.BigInteger;
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
    private UserDao userDao;
    
    @Override
    public List<Discussion> getNewestDiscussionList(int n) {
        return discussionDao.findNewestDiscussionList(n);
    }

    @Override
    public List<Discussion> getDiscussionListBefor(BigInteger id, int n) {
        return discussionDao.findDiscussionListBefor(id, n);
    }

    @Override
    public List<Discussion> getNewestDiscussionListByMainCategory(int n, int main_id) {
        return discussionDao.findNewestDiscussionListByMainCategory(n, main_id);
    }

    @Override
    public List<Discussion> getDiscussionListWithMainCategoryBefor(BigInteger id, int n, int main_id) {
        return discussionDao.findDiscussionListWithMainCategoryBefor(id, n, main_id);
    }

    @Override
    public List<Discussion> getNewestDiscussionListBySubCategory(int n, int main_id, int sub_id) {
        return discussionDao.findNewestDiscussionListBySubCategory(n, main_id, sub_id);
    }

    @Override
    public List<Discussion> getDiscussionListWithSubCategoryBefor(BigInteger id, int n, int main_id, int sub_id) {
        return discussionDao.findDiscussionListWithSubCategoryBefor(id, n, main_id, sub_id);
    }

    @Override
    public List<Map<String, String>> replaceAuthorIDToAurthorName(List<Discussion> dls) {
        List<Map<String, String>> mls = new ArrayList<>();
        for (Discussion d : dls) {
            String author_name = userDao.findUserByID(d.getAuthorID()).getName();
            mls.add(d.replaceAuthorIDToAuthorName(author_name));
        }
        return mls;
    }
}
