/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Discussion;
import com.tasktoys.archelon.service.CategoryService;
import com.tasktoys.archelon.service.DiscussionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller of index.jsp
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    
    @Autowired
    private CategoryService categorysService;
    @Autowired
    private DiscussionService discussionService;
    
    static final String VIEW = "index";
    private static final String CATEGORY_SELECTION = "category_selection";
    private static final String CREATE_DISCUSSION = "create_discussion";
    
    private static final int discussion_list_size = 10;
    
    private enum CategorySelectionParam {
        main_category_id, sub_category_id
    }
    
    private enum CategorySelectBox {
        main_category_list, sub_category_list
    }
    
    private static final String DISCUSSION_TABLE = "discussion_table";
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        makeMainCategory(model);
        makeNewestDiscussionTable(model);
        return VIEW;
    }
    
    @RequestMapping(value = "next/{id}", method = RequestMethod.GET)
    public String getNextDiscussionTable(@PathVariable Long id, Model model) {
        makeMainCategory(model);
        makeNewestDiscussionTableBefore(model, id);
        return VIEW;
    }
    
    @RequestMapping(value = "prev/{id}", method = RequestMethod.GET)
    public String getPreviousDiscussionTable(@PathVariable Long id, Model model) {
        makeMainCategory(model);
        makeNewestDiscussionTableAfter(model, id);
        return VIEW;
    }
    
    @RequestMapping(value = "next/{id}/main_id/{main_id}", method = RequestMethod.GET)
    public String getNextDiscussionTableWithMainID(@PathVariable Long id, @PathVariable int main_id, Model model) {
        makeMainCategory(model, main_id);
        makeDiscussionTableBefore(model, id, main_id);
        return VIEW;
    }
    
        @RequestMapping(value = "prev/{id}/main_id/{main_id}", method = RequestMethod.GET)
    public String getPreviousDiscussionTableWithMainID(@PathVariable Long id, @PathVariable int main_id, Model model) {
        makeMainCategory(model, main_id);
        makeDiscussionTableBefore(model, id, main_id);
        return VIEW;
    }
    
    @RequestMapping(value = CATEGORY_SELECTION, method = RequestMethod.POST)
    public String chooseCategory(@RequestParam Map<String, String> params, Model model) {
        try {
            Integer main_id = Integer.valueOf(params.get(CategorySelectionParam.main_category_id.name()));
            makeMainCategory(model, main_id);
            try {
                Integer sub_id = Integer.valueOf(params.get(CategorySelectionParam.sub_category_id.name()));
                makeSubCategory(model, main_id, sub_id);
                makeDiscussionTable(model, main_id, sub_id);
            } catch (NumberFormatException e) {
                makeSubCategory(model, main_id);
                makeDiscussionTable(model, main_id);
            }
        } catch (NumberFormatException e) {
            makeMainCategory(model);
            makeNewestDiscussionTable(model);
        }
        return VIEW;
    }
    
    @RequestMapping(value = CREATE_DISCUSSION, method = RequestMethod.POST)
    public String createDiscussion(Model model) {
        return VIEW;
    }
    
    private void makeMainCategory(Model model) {
        model.addAttribute(CategorySelectBox.main_category_list.name(),
                Category.list.toMapList(categorysService.getMainCategoryList()));
    }
    
    private void makeMainCategory(Model model, int selected_id) {
        model.addAttribute(CategorySelectBox.main_category_list.name(),
                Category.list.toMapList(categorysService.getMainCategoryList(), selected_id));
    }
    
    private void makeSubCategory(Model model, int selected_main_id) {
        model.addAttribute(CategorySelectBox.sub_category_list.name(),
                Category.list.toMapList(categorysService.getSubCategoryList(selected_main_id)));
    }
    
    private void makeSubCategory(Model model, int selected_main_id, int selected_sub_id) {
        model.addAttribute(CategorySelectBox.sub_category_list.name(),
                Category.list.toMapList(categorysService.getSubCategoryList(selected_main_id), selected_sub_id));
    }
    
    private void makeNewestDiscussionTable(Model model) {
        List<Discussion> dls = discussionService.getNewestDiscussionList(discussion_list_size);
        List<Map<String, String>> mls = discussionService.replaceAuthorIDToAurthorName(dls);
        model.addAttribute(DISCUSSION_TABLE, mls);
        model.addAttribute("last_discussion_id", dls.get(dls.size() - 1).getID().toString());
    }
    
    private void makeNewestDiscussionTableAfter(Model model, Long id) {
        model.addAttribute(DISCUSSION_TABLE,
                discussionService.replaceAuthorIDToAurthorName(
                        discussionService.getDiscussionListAfter(id, discussion_list_size)));
    }
    
    private void makeNewestDiscussionTableBefore(Model model, Long id) {
        model.addAttribute(DISCUSSION_TABLE,
                discussionService.replaceAuthorIDToAurthorName(
                        discussionService.getDiscussionListBefor(id, discussion_list_size)));
    }
    
    private void makeDiscussionTable(Model model, int main_id) {
        model.addAttribute(DISCUSSION_TABLE,
                discussionService.replaceAuthorIDToAurthorName(
                        discussionService.getNewestDiscussionListByMainCategory(discussion_list_size, main_id)));
    }
    
    private void makeDiscussionTableBefore(Model model, Long id, int main_id) {
        model.addAttribute(DISCUSSION_TABLE,
                discussionService.replaceAuthorIDToAurthorName(
                        discussionService.getDiscussionListWithMainCategoryBefor(id, discussion_list_size, main_id)));
    }
    
    private void makeDiscussionTable(Model model, int main_id, int sub_id) {
        model.addAttribute(DISCUSSION_TABLE,
                discussionService.replaceAuthorIDToAurthorName(
                        discussionService.getNewestDiscussionListBySubCategory(discussion_list_size, main_id, sub_id)));
    }
    
}
