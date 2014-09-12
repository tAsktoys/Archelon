/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.data.entity.DiscussionContent.Post;
import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.DiscussionContentService;
import com.tasktoys.archelon.service.DiscussionService;
import com.tasktoys.archelon.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Controller of discussion.jsp
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/discussion")
@SessionAttributes(UserSession.SESSION_NAME)
public class DiscussionController {
    
    @Autowired
    private Properties properties;
    @Autowired
    private UserService userService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private DiscussionContentService discussionContentService;
    
    protected static final String VIEW = "discussion";

    private static final String ID = "id";
    private static final String THEME = "theme";
    private static final String DISCUSSION_LOG = "discussion_log";
    private static final String TYPE = "type";
    private static final String ICON = "icon";
    private static final String USERPAGE = "userpage";
    private static final String USERNAME = "username";
    private static final String MESSAGE = "message";
    private static final String POSTEDMESSAGE = "postedMessage";

    @RequestMapping(value = "{" + ID + "}", method = RequestMethod.GET)
    public String handleGet(@PathVariable long id, Model model) {
        updatePage(model, id);
        return VIEW;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = POSTEDMESSAGE)
    public String handlePost(@PathVariable long id, @RequestParam Map<String, String> params, Model model,
            UserSession userSession) {
        Post post = makePost(params, userSession.getUser());
        discussionContentService.insertPost(id, post);
        updateDiscussionProperties(id);
        updatePage(model, id);
        return VIEW;
    }
    
    private void updatePage(Model model, long discussionId) {
        DiscussionContent content = discussionContentService.getDiscussionContent(discussionId);
        updateDiscussionTheme(model, content.getSubject());
        updateDiscussionLog(model, content);
        model.addAttribute(ID, discussionId);
    }

    private void updateDiscussionTheme(Model model, String subject) {
        model.addAttribute(THEME, subject);
    }

    private void updateDiscussionLog(Model model, DiscussionContent content) {
        List<Map<String, String>> list = new ArrayList<>();
        for (DiscussionContent.Post post : content.getPosts()) {
            list.add(putPost(post));
        }

        model.addAttribute(DISCUSSION_LOG, list);
    }

    private Map<String, String> putPost(DiscussionContent.Post post) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TYPE, "others");
        map.put(ICON, "hoge");
        String userName = userService.findUserById(post.getAuthorId()).getName();
        map.put(USERPAGE, properties.getProperty("contextpath") + "user/" + userName);
        map.put(USERNAME, userName);
        map.put(MESSAGE, post.getDescription());
        return map;
    }
    
    private DiscussionContent.Post makePost(Map<String, String> params, User user) {
        Post post = new Post();
        post.setAuthorId(user.getId());
        post.setDescription(params.get(POSTEDMESSAGE));
        return post;
    }
    
    private void updateDiscussionProperties(long discussionId) {
        
        discussionService.incrementPosts(discussionId);
        discussionService.updateUpdateTime(discussionId);

    }
}
