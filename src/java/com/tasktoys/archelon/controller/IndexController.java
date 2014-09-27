/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.service.ActivityService;
import com.tasktoys.archelon.service.CategoryService;
import com.tasktoys.archelon.service.DiscussionService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    private enum CategorySelectionParam {

        MAIN_CATEGORY_ID, SUB_CATEGORY_ID;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private enum CreateDiscussionParam {

        SUBJECT, MAIN_CATEGORY_ID, SUB_CATEGORY_ID, DESCRIPTION;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private static final String DISCUSSION_LIST = "discussion_list";
    private static final int DISCUSSION_LIST_SIZE = 10;

    private static final String PAGE_NUMBER_LIST = "pageNumberList";
    private static final String MAIN_ID = "mainId";
    private static final String SUB_ID = "subId";

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final String PREVIOUS_PAGE_NUMBER = "previousPageNumber";
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    private static final String NEXT_PAGE_NUMBER = "nextPageNumber";

    private static final String ACTIVITY_LIST = "activity_list";
    private static final int ACTIVITY_LIST_SIZE = 5;

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(Model model) {
        model.addAllAttributes(createMainCategories());
        model.addAllAttributes(createDiscussions());
        model.addAllAttributes(createDiscussionLink(DEFAULT_PAGE_NUMBER));
        model.addAllAttributes(activityService.createActivities(ACTIVITY_LIST, ACTIVITY_LIST_SIZE));
        return VIEW;
    }

    @RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
    public String handleNewPageRequest(@PathVariable int pageNumber, Model model) {
        model.addAllAttributes(createMainCategories());
        model.addAllAttributes(createDiscussions(calculateOffset(pageNumber)));
        model.addAllAttributes(createDiscussionLink(pageNumber));
        model.addAllAttributes(activityService.createActivities(ACTIVITY_LIST, ACTIVITY_LIST_SIZE));
        return VIEW;
    }

    @RequestMapping(value = "page/{pageNumber}/mainid/{mainId}", method = RequestMethod.GET)
    public String handleNewPageRequestWithMainId(@PathVariable int pageNumber,
            @PathVariable int mainId, Model model) {
        model.addAllAttributes(createCategorySelects(mainId));
        model.addAllAttributes(createDiscussionsByMainCategory(
                mainId, calculateOffset(pageNumber)));
        model.addAllAttributes(createDiscussionLink(pageNumber, mainId));
        model.addAllAttributes(activityService.createActivities(ACTIVITY_LIST, ACTIVITY_LIST_SIZE));
        return VIEW;
    }

    @RequestMapping(value = "page/{pageNumber}/mainid/{mainId}/subid/{subId}", method = RequestMethod.GET)
    public String handleNewPageRequestWithSubId(@PathVariable int pageNumber,
            @PathVariable int mainId, @PathVariable int subId, Model model) {
        model.addAllAttributes(createCategorySelects(mainId));
        model.addAllAttributes(createDiscussionsBySubCategory(
                subId, calculateOffset(pageNumber)));
        model.addAllAttributes(createDiscussionLink(pageNumber, mainId, subId));
        model.addAllAttributes(activityService.createActivities(ACTIVITY_LIST, ACTIVITY_LIST_SIZE));
        return VIEW;
    }

    @RequestMapping(value = CATEGORY_SELECTION, method = RequestMethod.POST)
    public String handleCategorySelect(@RequestParam Map<String, String> params, Model model) {
        makeCategorySelect(model,
                params.get(CategorySelectionParam.MAIN_CATEGORY_ID.toString()),
                params.get(CategorySelectionParam.SUB_CATEGORY_ID.toString()));
        model.addAllAttributes(activityService.createActivities(ACTIVITY_LIST, ACTIVITY_LIST_SIZE));
        return VIEW;
    }

    @RequestMapping(value = CREATE_DISCUSSION, method = RequestMethod.POST)
    public String handleCreateDiscussion(@RequestParam Map<String, String> params,
            Model model, UserSession userSession) {
        if (hasAllParameters(params)) {
            try {
                discussionService.insertDiscussion(makeNewDiscussion(params, userSession),
                        makeNewDiscussionContent(params, userSession));
                activityService.discussionMadeBy(userSession.getUser());
            } catch (DuplicateKeyException e) {
                activityService.discussionMadeBy(userSession.getUser());
            }
        } else {
            makeCategorySelect(model,
                    params.get(CategorySelectionParam.MAIN_CATEGORY_ID.toString()),
                    params.get(CategorySelectionParam.SUB_CATEGORY_ID.toString()));
        }
        model.addAllAttributes(activityService.createActivities(ACTIVITY_LIST, ACTIVITY_LIST_SIZE));
        return VIEW;
    }

    private int calculateOffset(int currentPageNumber) {
        return (currentPageNumber - 1) * DISCUSSION_LIST_SIZE;
    }

    private boolean hasAllParameters(Map<String, String> params) {
        for (String key : params.keySet()) {
            // the smallest category is used for category_id.
            if (key.equals(CreateDiscussionParam.SUB_CATEGORY_ID.toString())
                    && !isChoosenSelection(params.get(key))) {
                return false;
            }
            if (params.get(key).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Discussion makeNewDiscussion(Map<String, String> params, UserSession userSession) {
        String subject = params.get(CreateDiscussionParam.SUBJECT.toString());
        String category = params.get(CreateDiscussionParam.SUB_CATEGORY_ID.toString());

        Discussion.Builder builder = new Discussion.Builder();
        builder.subject(subject);
        builder.categoryID(Integer.parseInt(category));
        builder.authorID(userSession.getUser().getId());
        return builder.buildForInsert();
    }

    private DiscussionContent makeNewDiscussionContent(Map<String, String> params, UserSession userSession) {
        String subject = params.get(CreateDiscussionParam.SUBJECT.toString());
        DiscussionContent content = new DiscussionContent();

        content.setSubject(subject);
        long userId = userSession.getUser().getId();
        content.addPost(userId);
        content.addParticipants(userId);
        return content;
    }

    private void makeCategorySelect(Model model, String main, String sub) {
        boolean isMainIdChoosen = isChoosenSelection(main);
        boolean isSubIdChoosen = isChoosenSelection(sub);

        if (isMainIdChoosen && isSubIdChoosen) {
            int mainId = Integer.valueOf(main);
            int subId = Integer.valueOf(sub);
            model.addAllAttributes(createCategorySelects(mainId));
            model.addAllAttributes(createDiscussionsBySubCategory(subId));
            model.addAllAttributes(createDiscussionLink(DEFAULT_PAGE_NUMBER, mainId, subId));
            model.addAttribute(MAIN_ID, mainId);
            model.addAttribute(SUB_ID, subId);
        } else if (isMainIdChoosen) {
            int mainId = Integer.valueOf(main);
            model.addAllAttributes(createCategorySelects(mainId));
            model.addAllAttributes(createDiscussionsByMainCategory(mainId));
            model.addAllAttributes(createDiscussionLink(DEFAULT_PAGE_NUMBER, mainId));
            model.addAttribute(MAIN_ID, mainId);
        } else {
            model.addAllAttributes(createMainCategories());
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

    private Map<String, List<Map<String, String>>> createCategorySelects(int mainId) {
        Map<String, List<Map<String, String>>> subModel = new HashMap<>();
        subModel.putAll(createMainCategories());
        subModel.putAll(createSubCategories(mainId));
        return subModel;
    }

    private Map<String, List<Map<String, String>>> createMainCategories() {
        return Collections.singletonMap(MAIN_CATEGORY_LIST,
                toMapList(categoryService.getMainCategoryList()));
    }

    private Map<String, List<Map<String, String>>> createSubCategories(int selectedMainId) {
        return Collections.singletonMap(SUB_CATEGORY_LIST,
                toMapList(categoryService.getSubCategoryList(selectedMainId)));
    }

    private Map<String, List<Map<String, String>>> createDiscussions() {
        return Collections.singletonMap(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionList(DISCUSSION_LIST_SIZE)));
    }

    private Map<String, List<Map<String, String>>> createDiscussions(int offset) {
        return Collections.singletonMap(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListWithOffset(
                                DISCUSSION_LIST_SIZE, offset)));
    }

    private Map<String, List<Map<String, String>>> createDiscussionsByMainCategory(int mainId) {
        return Collections.singletonMap(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListByMainCategory(
                                DISCUSSION_LIST_SIZE, mainId)));
    }

    private Map<String, List<Map<String, String>>> createDiscussionsByMainCategory(int mainId, int offset) {
        return Collections.singletonMap(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListByMainCategoryWithOffset(
                                DISCUSSION_LIST_SIZE, mainId, offset)));
    }

    private Map<String, List<Map<String, String>>> createDiscussionsBySubCategory(int subId) {
        return Collections.singletonMap(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListBySubCategory(
                                DISCUSSION_LIST_SIZE, subId)));
    }

    private Map<String, List<Map<String, String>>> createDiscussionsBySubCategory(int subId, int offset) {
        return Collections.singletonMap(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListBySubCategoryWithOffset(
                                DISCUSSION_LIST_SIZE, subId, offset)));
    }

    private Map<String, Object> createDiscussionLink(int currentPageNumber) {
        int endPageNumber = calculateEndPageNumber(discussionService.countDiscussion());
        return createPageNumbers(currentPageNumber, endPageNumber);
    }

    private int calculateEndPageNumber(int discussionNumber) {
        return (int) Math.ceil((double) discussionNumber / DISCUSSION_LIST_SIZE);
    }

    private Map<String, Object> createDiscussionLink(int currentPageNumber, int mainId) {
        int endPageNumber = calculateEndPageNumber(discussionService.countDiscussionByMainCategory(mainId));
        Map<String, Object> subModel = new HashMap<>();
        subModel.putAll(createPageNumbers(currentPageNumber, endPageNumber));
        subModel.put(MAIN_ID, mainId);
        return subModel;
    }

    private Map<String, Object> createDiscussionLink(int currentPageNumber, int mainId, int subId) {
        int endPageNumber = calculateEndPageNumber(discussionService.countDiscussionBySubCategory(subId));
        Map<String, Object> subModel = new HashMap<>();
        subModel.putAll(createPageNumbers(currentPageNumber, endPageNumber));
        subModel.put(MAIN_ID, mainId);
        subModel.put(SUB_ID, subId);
        return subModel;
    }

    private Map<String, Object> createPageNumbers(int currentPageNumber, int endPageNumber) {
        Map<String, Object> subModel = new HashMap<>();
        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= endPageNumber; i++) {
            pageNumberList.add(i);
        }
        subModel.put(PAGE_NUMBER_LIST, pageNumberList);
        int previousPageNumber = currentPageNumber - 1;
        int nextPageNumber = currentPageNumber + 1;
        if (currentPageNumber >= 1) {
            previousPageNumber = 1;
        }
        if (endPageNumber <= nextPageNumber) {
            nextPageNumber = endPageNumber;
        }
        subModel.put(PREVIOUS_PAGE_NUMBER, previousPageNumber);
        subModel.put(CURRENT_PAGE_NUMBER, currentPageNumber);
        subModel.put(NEXT_PAGE_NUMBER, nextPageNumber);
        return subModel;
    }

    private List<Map<String, String>> toMapList(List<Category> categories) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Category category : categories) {
            Map<String, String> map = new HashMap<>(2);
            map.put("id", Integer.toString(category.getId()));
            map.put("name", category.getName());
            list.add(map);
        }
        return list;
    }
}
