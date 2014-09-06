/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.data.entity.DiscussionContent;
import com.tasktoys.archelon.service.CategoryService;
import com.tasktoys.archelon.service.DiscussionService;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private CategoryService categorysService;
    @Autowired
    private DiscussionService discussionService;

    protected static final String VIEW = "index";
    protected static final String REDIRECT = "redirect:/";

    private static final String CATEGORY_SELECTION = "category_selection";
    private static final String CREATE_DISCUSSION = "create_discussion";

    private enum CategorySelectionParam {

        MAIN_CATEGORY_ID, SUB_CATEGORY_ID;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private enum CategorySelectBox {

        MAIN_CATEGORY_LIST, SUB_CATEGORY_LIST;

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
    private static final int DISCUSSION_LIST_SIZE = 3;

    private static final String ACTIVITY_LIST = "activity_list";
    private static final int ACTIVITY_LIST_SIZE = 5;

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(Model model) {
        makeMainCategory(model);
        makeNewestDiscussionList(model);
        makeDiscussionLink(model, 1);
        makeActivityList(model);
        return VIEW;
    }

    @RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
    public String handleNewPageRequest(@PathVariable int pageNumber, Model model) {
        makeMainCategory(model);
        makeNewestDiscussionListWithOffset(model, (pageNumber - 1) * DISCUSSION_LIST_SIZE);
        makeDiscussionLink(model, pageNumber);
        makeActivityList(model);
        return VIEW;
    }
    
    @RequestMapping(value = "page/{pageNumber}/mainid/{mainId}", method = RequestMethod.GET)
    public String handleNewPageRequestWithMainId(@PathVariable int pageNumber,
            @PathVariable int mainId, Model model) {
        return VIEW;
    }
    
    @RequestMapping(value = "page/{pageNumber}/mainid/{mainId}/subid/[subId}", method = RequestMethod.GET)
    public String handleNewPageRequestWithSubId(@PathVariable int pageNumber,
            @PathVariable int mainId, @PathVariable int subId,Model model) {
        return REDIRECT;
    }
//    @RequestMapping(value = "next/{discussion_id}", method = RequestMethod.GET)
//    public String getNextDiscussionList(@PathVariable long discussion_id, Model model) {
//        makeMainCategory(model);
//        makeNewestDiscussionListBefore(model, discussion_id);
//        makeActivityList(model);
//        return VIEW;
//    }
//
//    @RequestMapping(value = "prev/{discussion_id}", method = RequestMethod.GET)
//    public String getPreviousDiscussionList(@PathVariable long discussion_id, Model model) {
//        makeMainCategory(model);
//        makeNewestDiscussionListAfter(model, discussion_id);
//        makeActivityList(model);
//        return VIEW;
//    }
//
//    @RequestMapping(value = "next/{discussion_id}/main_category_id/{main_category_id}", method = RequestMethod.GET)
//    public String getNextDiscussionListWithMainID(@PathVariable long discussion_id,
//            @PathVariable int main_category_id, Model model) {
//        makeMainCategory(model, main_category_id);
//        makeDiscussionListBefore(model, discussion_id, main_category_id);
//        makeActivityList(model);
//        return VIEW;
//    }
//
//    @RequestMapping(value = "prev/{discussion_id}/main_category_id/{main_id}", method = RequestMethod.GET)
//    public String getPreviousDiscussionListWithMainID(@PathVariable long discussion_id,
//            @PathVariable int main_category_id, Model model) {
//        makeMainCategory(model, main_category_id);
//        makeDiscussionListBefore(model, discussion_id, main_category_id);
//        makeActivityList(model);
//        return VIEW;
//    }

    @RequestMapping(value = CATEGORY_SELECTION, method = RequestMethod.POST)
    public String handleCategorySelect(@RequestParam Map<String, String> params, Model model) {
        makeCategorySelect(model,
                params.get(CategorySelectionParam.MAIN_CATEGORY_ID.toString()),
                params.get(CategorySelectionParam.SUB_CATEGORY_ID.toString()));
        makeActivityList(model);
        return VIEW;
    }

    @RequestMapping(value = CREATE_DISCUSSION, method = RequestMethod.POST)
    public String handleCreateDiscussion(@RequestParam Map<String, String> params,
            Model model, UserSession userSession) {
        if (hasAllParameters(params)) {
            discussionService.insertDiscussion(makeNewDiscussion(params, userSession),
                    makeNewDiscussionContent(params, userSession));
        } else {
            makeCategorySelect(model,
                    params.get(CategorySelectionParam.MAIN_CATEGORY_ID.toString()),
                    params.get(CategorySelectionParam.SUB_CATEGORY_ID.toString()));
        }
        makeActivityList(model);
        return VIEW;
    }

    private boolean hasAllParameters(Map<String, String> params) {
        for (String key : params.keySet()) {
            // the smallest category is used for category_id.
            // if sub_category_id is not choosed, i.e., all is choosed,
            // "" is in params.get("sub_category_id")
            if (key.equals(CreateDiscussionParam.SUB_CATEGORY_ID.toString())) {
                try {
                    Integer.parseInt(params.get(CreateDiscussionParam.SUB_CATEGORY_ID.toString()));
                } catch (NumberFormatException e) {
                    return false;
                }
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
        String description = params.get(CreateDiscussionParam.DESCRIPTION.toString());
        DiscussionContent content = new DiscussionContent();
        
        content.setSubject(CreateDiscussionParam.SUBJECT.toString());
        content.setDescription(description);
        content.addPost(userSession.getUser().getId());
        return content;
    }

    private void makeCategorySelect(Model model, String main, String sub) {
        boolean isMainIdChoosen = isChoosenSelection(main);
        boolean isSubIdChoosen = isChoosenSelection(sub);
        
        if (isMainIdChoosen && isSubIdChoosen) {
            int mainId = Integer.valueOf(main);
            int subId = Integer.valueOf(sub);
            makeMainCategory(model, mainId);
            makeSubCategory(model, mainId, subId);
            makeDiscussionListBySubCategory(model, subId);
        } else if (isMainIdChoosen) {
            int mainId = Integer.valueOf(main);
            makeMainCategory(model, mainId);
            makeSubCategory(model, mainId);
            makeDiscussionListByMainCategory(model, mainId);
        } else {
            makeMainCategory(model);
            makeNewestDiscussionList(model);
        }
    }
    
    private boolean isChoosenSelection(String categoryId) {
        try {
            Integer.valueOf(categoryId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void makeMainCategory(Model model) {
        model.addAttribute(CategorySelectBox.MAIN_CATEGORY_LIST.toString(),
                Category.list.toMapList(categorysService.getMainCategoryList()));
    }

    private void makeMainCategory(Model model, int selected_id) {
        model.addAttribute(CategorySelectBox.MAIN_CATEGORY_LIST.toString(),
                Category.list.toMapList(categorysService.getMainCategoryList(), selected_id));
    }

    private void makeSubCategory(Model model, int selected_main_id) {
        model.addAttribute(CategorySelectBox.SUB_CATEGORY_LIST.toString(),
                Category.list.toMapList(categorysService.getSubCategoryList(selected_main_id)));
    }

    private void makeSubCategory(Model model, int selected_main_id, int selected_sub_id) {
        model.addAttribute(CategorySelectBox.SUB_CATEGORY_LIST.toString(),
                Category.list.toMapList(categorysService.getSubCategoryList(selected_main_id), selected_sub_id));
    }

    private void makeNewestDiscussionList(Model model) {
        model.addAttribute(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionList(DISCUSSION_LIST_SIZE)));
    }
    
    private void makeNewestDiscussionListWithOffset(Model model, int offset) {
        model.addAttribute(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListWithOffset(DISCUSSION_LIST_SIZE, offset)));
    }

//    private void makeNewestDiscussionListAfter(Model model, long id) {
//        model.addAttribute(DISCUSSION_LIST,
//                discussionService.replaceAuthorIDToAuthorName(
//                        discussionService.getDiscussionListAfter(id, DISCUSSION_LIST_SIZE)));
//    }
//
//    private void makeNewestDiscussionListBefore(Model model, long id) {
//        model.addAttribute(DISCUSSION_LIST,
//                discussionService.replaceAuthorIDToAuthorName(
//                        discussionService.getDiscussionListBefore(id, DISCUSSION_LIST_SIZE)));
//    }

    private void makeDiscussionListByMainCategory(Model model, int main_id) {
        model.addAttribute(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListByMainCategory(DISCUSSION_LIST_SIZE, main_id)));
    }

//    private void makeDiscussionListBefore(Model model, long id, int main_id) {
//        model.addAttribute(DISCUSSION_LIST,
//                discussionService.replaceAuthorIDToAuthorName(
//                        discussionService.getDiscussionListWithMainCategoryBefore(id, DISCUSSION_LIST_SIZE, main_id)));
//    }

    private void makeDiscussionListBySubCategory(Model model, int subId) {
        model.addAttribute(DISCUSSION_LIST,
                discussionService.replaceAuthorIDToAuthorName(
                        discussionService.getNewestDiscussionListBySubCategory(DISCUSSION_LIST_SIZE, subId)));
    }

    private void makeDiscussionLink(Model model, int currentPageNumber) {
        int endPageNumber = (int)(discussionService.countDiscussion() / DISCUSSION_LIST_SIZE) + 1;
        List<Integer> nls = new ArrayList<>();
        for (int i = 1; i <= endPageNumber; i++) {
            nls.add(i);
        }
        model.addAttribute("pageNumberList", nls);
        setPageNumbers(model, currentPageNumber, endPageNumber);
    }
    
    private void setPageNumbers(Model model, int currentPageNumber, int endPageNumber) {
        int previousPageNumber = currentPageNumber - 1;
        int nextPageNumber = currentPageNumber + 1;
        
        if (currentPageNumber >= 1)
            previousPageNumber = 1;
        if (endPageNumber <= nextPageNumber)
            nextPageNumber = endPageNumber;
        
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
    }
    
    private void makeDiscussionLinkWithMainId(Model model, int mainId) {
        
    }
    
    private void makeDiscussionLinkWithSubId(Model model, int mainId, int subId) {
        
    }
    
    private void makeActivityList(Model model) {
        model.addAttribute(ACTIVITY_LIST, ActivityDaoStub.findNewestActivity(ACTIVITY_LIST_SIZE));
    }

    private static class ActivityDaoStub {

        public static List<Map<String, String>> findNewestActivity(int n) {
            List<Map<String, String>> als = new ArrayList<>();
            while (als.size() < n) {
                als.add(makeActivity());
            }
            return als;
        }

        private static Map<String, String> makeActivity() {
            Map<String, String> map = new HashMap<>();
            map.put("time", makeTime());
            map.put("act", makeAct());
            return map;
        }

        private static String makeTime() {
            int i = (int) (Math.random() * 5);
            if (i == 0) {
                return "Just now";
            }
            return i + " minutes ago";
        }

        private static String makeAct() {
            int n = (int) (Math.random() * 3);
            if (n == 0) {
                return "Someone made new discussion \"What's up?\"in sub_category1";
            } else if (n == 1) {
                return "Someone's discussion is ranked as the hottest discussion in main_category1";
            }
            return "Discussion \"How to make a simple web service\" is closed";
        }
    }
}
