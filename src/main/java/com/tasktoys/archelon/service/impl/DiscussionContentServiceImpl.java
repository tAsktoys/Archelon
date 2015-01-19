/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.mongodb.MongoDbDiscussionContentDao;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.data.entity.DiscussionContent.Post;
import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.DiscussionContentService;
import com.tasktoys.archelon.service.DiscussionService;
import com.tasktoys.archelon.service.UserService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YuichiroSato
 * @author mikan
 * @version 0.1
 */
@Service
public class DiscussionContentServiceImpl implements DiscussionContentService {

    @Autowired
    private Properties properties;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UserService userService;
    @Autowired
    private MongoDbDiscussionContentDao mongoDbDiscussionContentDao;

    private static final String TYPE = "type";
    private static final String ICON = "icon";
    private static final String USERPAGE = "userpage";
    private static final String USERNAME = "username";
    private static final String MESSAGE = "message";
    private static final String POSTTIMESTAMP = "postTimeStamp";

    private DiscussionContent getDiscussionContent(long discussionId) {
        return mongoDbDiscussionContentDao.findByDiscussionId(discussionId);
    }

    private Post getLastPost(long discussionId) {
        List<Post> posts = mongoDbDiscussionContentDao.findByDiscussionId(discussionId).getPosts();
        return posts.get(posts.size() - 1);
    }

    @Override
    public void insertPost(long discussionId, Post post, User author) {
        if (author != null) {
            post.setAuthorId(author.getId());
        }
        Post lastPost = getLastPost(discussionId);
        if (!post.equals(lastPost)) {
            mongoDbDiscussionContentDao.insertPost(discussionId, post);
            discussionService.updateDiscussionProperties(discussionId, author);
        }

    }

    @Override
    public Map<String, Map<String, Object>> createDiscussionContent(String name,
            long discussionId, User user, Locale locale) {
        DiscussionContent content = getDiscussionContent(discussionId);
        Map<String, Object> subModel = new HashMap<>();

        subModel.put("subject", content.getSubject());
        subModel.put("posts", createPostsList(content.getPosts(), user, createDateFormat(locale)));
        return Collections.singletonMap(name, subModel);
    }

    private List<Map<String, String>> createPostsList(List<Post> posts, User user, DateFormat dateFormat) {
        List<Map<String, String>> postList = new ArrayList<>();
        for (Post post : posts) {
            postList.add(toMap(post, user, dateFormat));
        }
        return postList;
    }

    private Map<String, String> toMap(Post post, User user, DateFormat dateFormat) {
        Map<String, String> map = new HashMap<>();
//        map.put(ICON, "hoge"); // TODO: link to user icon image
        Long authorId = post.getAuthorId();
        if (authorId != null) {
            String userName = userService.findUserById(post.getAuthorId()).getName();
            map.put(USERPAGE, properties.getProperty("contextpath") + "user/" + userName);
            map.put(USERNAME, userName);
            if (user != null && user.getName().equals(userName)) {
                map.put(TYPE, "me");
            } else {
                map.put(TYPE, "others");
            }
        }
        map.put(MESSAGE, post.getDescription());
        if (dateFormat == null) {
            dateFormat = createDateFormat(null);
        }
        map.put(POSTTIMESTAMP, dateFormat.format(post.getPostTimeStamp()));
        return map;
    }

    private DateFormat createDateFormat(Locale locale) {
        if (locale == null) {
            return new SimpleDateFormat();
        } else if (locale.equals(Locale.JAPAN) || locale.equals(Locale.JAPANESE)) {
            return new SimpleDateFormat("yyyy/MMM/dd (EEE) HH:mm:ss z", locale);
        } else {
            return new SimpleDateFormat();
        }
    }
}
