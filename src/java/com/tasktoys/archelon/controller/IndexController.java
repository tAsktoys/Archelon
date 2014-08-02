/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.service.CategoryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    protected static final String VIEW = "index";
    protected static final String REDIRECT = "redirect:/";
    
    private static final String CATEGORY_SELECTION = "category_selection";
    private static final String CREATE_DISCUSSION = "create_discussion";
    
    private enum CategorySelectionParam {
        main_category_id, sub_category_id
    }
    
    private enum CategorySelectBox {
        main_category_list, sub_category_list
    }
    
    private static final String DISCUSSION_TABLE = "discussion_table";
    
    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(Model model) {
        makeMainCategory(model);
        makeNewestDiscussionTable(model);
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
    
    private List<Map<String, String>> discussionDaoStub1() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("no", "1");
        map1.put("title", "newest");
        map1.put("author", "mikan");
        map1.put("date", "20YY/MM/DD");
        list.add(map1);
        
        Map<String, String> map2 = new HashMap<>();
        map2.put("no", "2");
        map2.put("title", "newest");
        map2.put("author", "mikan");
        map2.put("date", "20YY/MM/DD");
        list.add(map2);
        return list;
    }
    
    private void makeNewestDiscussionTable(Model model) {
        model.addAttribute(DISCUSSION_TABLE, discussionDaoStub1());
    }
    
    private List<Map<String, String>> discussionDaoStub2(int main_id) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("no", "1");
        map1.put("title", "main category is " + main_id + ". sub category is ?");
        map1.put("author", "mikan");
        map1.put("date", "20YY/MM/DD");
        list.add(map1);
        
        Map<String, String> map2 = new HashMap<>();
        map2.put("no", "2");
        map2.put("title", "main category is " + main_id + ". sub category is ?");
        map2.put("author", "mikan");
        map2.put("date", "20YY/MM/DD");
        list.add(map2);
        return list;
    }
    
    private void makeDiscussionTable(Model model, int main_id) {
        model.addAttribute(DISCUSSION_TABLE, discussionDaoStub2(main_id));
    }
    
    private List<Map<String, String>> discussionDaoStub3(int main_id, int sub_id) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("no", "1");
        map1.put("title", "main category is " + main_id + ". sub category is " + sub_id);
        map1.put("author", "mikan");
        map1.put("date", "20YY/MM/DD");
        list.add(map1);
        
        Map<String, String> map2 = new HashMap<>();
        map2.put("no", "2");
        map2.put("title", "main category is " + main_id + ". sub category is " + sub_id);
        map2.put("author", "mikan");
        map2.put("date", "20YY/MM/DD");
        list.add(map2);
        return list;
    }
    
    private void makeDiscussionTable(Model model, int main_id, int sub_id) {
        model.addAttribute(DISCUSSION_TABLE, discussionDaoStub3(main_id, sub_id));
    }
    
}
