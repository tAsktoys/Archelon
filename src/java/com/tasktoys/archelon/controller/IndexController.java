/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.User;
import com.tasktoys.archelon.service.ActivityService;
import com.tasktoys.archelon.service.CategoryService;
import com.tasktoys.archelon.service.DiscussionService;
import java.util.HashMap;
import java.util.List;
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
 * Controller of index.jsp
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/")
@SessionAttributes(UserSession.SESSION_NAME)
public class IndexController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private ActivityService activityService;

    protected static final String VIEW = "index";
    protected static final String REDIRECT = "redirect:/";

    private static final String CATEGORY_SELECTION = "category_selection";
    private static final String CREATE_DISCUSSION = "create_discussion";
    private static final String MAIN_CATEGORY_LIST = "main_category_list";
    private static final String SUB_CATEGORY_LIST = "sub_category_list";
    private static final String PAGE_NUMBERS = "page_numbers";

    private enum CategorySelectionParam {

        MAIN_CATEGORY_ID, SUB_CATEGORY_ID;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private enum CreateDiscussionParam {

        SUBJECT, CATEGORY_ID, DESCRIPTION;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private static final String DISCUSSION_LIST = "discussion_list";
    private static final int DISCUSSION_LIST_SIZE = 10;

    private static final String MAIN_ID = "mainId";
    private static final String SUB_ID = "subId";

    private static final int DEFAULT_PAGE_NUMBER = 1;

    private static final String ACTIVITY_LIST = "activity_list";
    private static final int ACTIVITY_LIST_SIZE = 5;

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(Model model) {
        model.addAllAttributes(createMainCategorySelect());
        model.addAllAttributes(createDiscussions());
        model.addAllAttributes(createDiscussionLink(DEFAULT_PAGE_NUMBER));
        model.addAllAttributes(createActivityList());
        return VIEW;
    }

    @RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
    public String handleNewPageRequest(@PathVariable int pageNumber, Model model) {
        model.addAllAttributes(createMainCategorySelect());
        model.addAllAttributes(createDiscussions(pageNumber));
        model.addAllAttributes(createDiscussionLink(pageNumber));
        model.addAllAttributes(createActivityList());
        return VIEW;
    }

    @RequestMapping(value = "page/{pageNumber}/mainid/{mainId}", method = RequestMethod.GET)
    public String handleNewPageRequestWithMainId(@PathVariable int pageNumber,
            @PathVariable int mainId, Model model) {
        model.addAllAttributes(createCategorySelects(mainId));
        model.addAllAttributes(createDiscussionsByMainCategory(mainId, pageNumber));
        model.addAllAttributes(createDiscussionLinkByMainCategory(pageNumber, mainId));
        model.addAllAttributes(createActivityList());
        setChoosenCategoryId(model, mainId);
        return VIEW;
    }

    @RequestMapping(value = "page/{pageNumber}/mainid/{mainId}/subid/{subId}", method = RequestMethod.GET)
    public String handleNewPageRequestWithSubId(@PathVariable int pageNumber,
            @PathVariable int mainId, @PathVariable int subId, Model model) {
        model.addAllAttributes(createCategorySelects(mainId));
        model.addAllAttributes(createDiscussionsBySubCategory(subId, pageNumber));
        model.addAllAttributes(createDiscussionLinkBySubCategory(pageNumber, subId));
        model.addAllAttributes(createActivityList());
        setChoosenCategoryId(model, mainId, subId);
        return VIEW;
    }

    @RequestMapping(value = CATEGORY_SELECTION, method = RequestMethod.POST)
    public String handleCategorySelect(@RequestParam Map<String, String> params, Model model) {
        String mainId = params.get(CategorySelectionParam.MAIN_CATEGORY_ID.toString());
        String subId = params.get(CategorySelectionParam.SUB_CATEGORY_ID.toString());
        makeCategorySelect(model, mainId, subId);
        model.addAllAttributes(createActivityList());
        return VIEW;
    }

    @RequestMapping(value = CREATE_DISCUSSION, method = RequestMethod.POST)
    public String handleCreateDiscussion(@RequestParam Map<String, String> params,
            Model model, UserSession userSession) {
        if (hasAllParameters(params)) {
            String subject = params.get(CreateDiscussionParam.SUBJECT.toString());
            User author = userSession.getUser();
            int category = Integer.parseInt(params.get(CreateDiscussionParam.CATEGORY_ID.toString()));
            String description = params.get(CreateDiscussionParam.DESCRIPTION.toString());

            discussionService.saveNewDiscussion(subject, author, category, description);
        }
        model.addAllAttributes(createDiscussions());
        model.addAllAttributes(createActivityList());
        return VIEW;
    }

    private boolean hasAllParameters(Map<String, String> params) {
        for (String key : params.keySet()) {
            // the smallest category is used for category_id.
            if (key.equals(CreateDiscussionParam.CATEGORY_ID.toString())
                    && !isChoosenSelection(params.get(key))) {
                return false;
            }
            if (params.get(key).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void makeCategorySelect(Model model, String main, String sub) {
        boolean isMainIdChoosen = isChoosenSelection(main);
        boolean isSubIdChoosen = isChoosenSelection(sub);

        if (isMainIdChoosen && isSubIdChoosen) {
            int mainId = Integer.valueOf(main);
            int subId = Integer.valueOf(sub);
            model.addAllAttributes(createCategorySelects(mainId));
            model.addAllAttributes(createDiscussionsBySubCategory(subId));
            model.addAllAttributes(createDiscussionLinkBySubCategory(DEFAULT_PAGE_NUMBER, subId));
            setChoosenCategoryId(model, mainId, subId);
        } else if (isMainIdChoosen) {
            int mainId = Integer.valueOf(main);
            model.addAllAttributes(createCategorySelects(mainId));
            model.addAllAttributes(createDiscussionsByMainCategory(mainId));
            model.addAllAttributes(createDiscussionLinkByMainCategory(DEFAULT_PAGE_NUMBER, mainId));
            setChoosenCategoryId(model, mainId);
        } else {
            model.addAllAttributes(createMainCategorySelect());
            model.addAllAttributes(createDiscussions());
            model.addAllAttributes(createDiscussionLink(DEFAULT_PAGE_NUMBER));
        }
    }

    // if category is not choosen, "" is in it.
    private boolean isChoosenSelection(String categoryId) {
        try {
            Integer.valueOf(categoryId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Map<String, List<Map<String, String>>> createMainCategorySelect() {
        return categoryService.createMainCategories(MAIN_CATEGORY_LIST);
    }

    private Map<String, List<Map<String, String>>> createCategorySelects(int mainId) {
        Map<String, List<Map<String, String>>> subModel = new HashMap<>();
        subModel.putAll(createMainCategorySelect());
        subModel.putAll(categoryService.createSubCategories(SUB_CATEGORY_LIST, mainId));
        return subModel;
    }

    private Map<String, List<Map<String, String>>> createDiscussions() {
        return discussionService.createLatestDiscussionList(
                DISCUSSION_LIST, DISCUSSION_LIST_SIZE);
    }

    private Map<String, List<Map<String, String>>> createDiscussions(int pageNumber) {
        return discussionService.createDiscussionList(
                DISCUSSION_LIST, pageNumber, DISCUSSION_LIST_SIZE);
    }

    private Map<String, List<Map<String, String>>> createDiscussionsByMainCategory(int mainId) {
        return discussionService.createLatestDiscussionListByMainCategory(
                DISCUSSION_LIST, DISCUSSION_LIST_SIZE, mainId);
    }

    private Map<String, List<Map<String, String>>> createDiscussionsByMainCategory(int mainId, int pageNumber) {
        return discussionService.createDiscussionListByMainCategory(
                DISCUSSION_LIST, pageNumber, DISCUSSION_LIST_SIZE, mainId);
    }

    private Map<String, List<Map<String, String>>> createDiscussionsBySubCategory(int subId) {
        return discussionService.createLatestDiscussionListByMainCategory(
                DISCUSSION_LIST, DISCUSSION_LIST_SIZE, subId);
    }

    private Map<String, List<Map<String, String>>> createDiscussionsBySubCategory(int subId, int pageNumber) {
        return discussionService.createDiscussionListBySubCategory(
                DISCUSSION_LIST, pageNumber, DISCUSSION_LIST_SIZE, subId);
    }

    private Model setChoosenCategoryId(Model model, int mainId) {
        model.addAttribute(MAIN_ID, mainId);
        return model;
    }

    private Model setChoosenCategoryId(Model model, int mainId, int subId) {
        model.addAttribute(MAIN_ID, mainId);
        model.addAttribute(SUB_ID, subId);
        return model;
    }

    private Map<String, Map<String, Object>> createDiscussionLink(int currentPageNumber) {
        return discussionService.createDiscussionLink(
                PAGE_NUMBERS, DISCUSSION_LIST_SIZE, currentPageNumber);
    }

    private Map<String, Map<String, Object>> createDiscussionLinkByMainCategory(int currentPageNumber, int mainId) {
        return discussionService.createDiscussionLinkByMainCategory(
                PAGE_NUMBERS, DISCUSSION_LIST_SIZE, currentPageNumber, mainId);
    }

    private Map<String, Map<String, Object>> createDiscussionLinkBySubCategory(int currentPageNumber, int subId) {
        return discussionService.createDiscussionLinkBySubCategory(
                PAGE_NUMBERS, DISCUSSION_LIST_SIZE, currentPageNumber, subId);
    }

    private Map<String, List<Map<String, Object>>> createActivityList() {
        return activityService.createActivities(ACTIVITY_LIST, ACTIVITY_LIST_SIZE);
    }
}
