/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.DiscussionContent.Post;
import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.DiscussionContentService;
import java.util.Locale;
import java.util.Map;
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
    private DiscussionContentService discussionContentService;

    protected static final String VIEW = "discussion";

    private static final String ID = "id";
    private static final String DISCUSSION_CONTENT = "discussion_content";
    private static final String POSTEDMESSAGE = "postedMessage";

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String handleGet(@PathVariable long id, Model model, UserSession userSession, Locale locale) {
        model.addAllAttributes(createPage(id, userSession.getUser(), locale));
        model.addAttribute(ID, id);
        return VIEW;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = POSTEDMESSAGE)
    public String handlePost(@PathVariable long id, @RequestParam Map<String, String> params,
            Model model, Locale locale, UserSession userSession) {
        User author = userSession.getUser();
        discussionContentService.insertPost(id, makeAnonymosPost(params), author);
        model.addAllAttributes(createPage(id, author, locale));
        model.addAttribute(ID, id);
        return VIEW;
    }
    
    private Map<String, Map<String, Object>> createPage(long discussionId, User user, Locale locale) {
        return discussionContentService.createDiscussionContent(
                DISCUSSION_CONTENT, discussionId, user, locale);
    }
    
    private Post makeAnonymosPost(Map<String, String> params) {
        Post post = new Post();
        post.setDescription(params.get(POSTEDMESSAGE));
        return post;
    }
}
