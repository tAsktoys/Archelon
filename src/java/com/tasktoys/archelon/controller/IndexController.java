/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.data.entity.Categorys;
import com.tasktoys.archelon.service.CategorysService;
import com.tasktoys.archelon.service.impl.CategorysServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller of index.jsp
 *
 * @author mikan
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    
    
    private final CategorysServiceImpl categorysService = new CategorysServiceImpl();
    
    static final String VIEW = "index";
    private static final String MAIN_CATEGORY_LIST = "main_category_list";
    private static final String SUB_CATEGORY_LIST = "sub_category_list";

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute(MAIN_CATEGORY_LIST, categorysService.getMainCategoryList());
        return VIEW;
    }
    
    @RequestMapping(method = RequestMethod.POST, params = "main_category")
    public String chooseCategory(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute(MAIN_CATEGORY_LIST, categorysService.getMainCategoryList());
        model.addAttribute(SUB_CATEGORY_LIST, categorysService.getSubCategoryList(params.get("main_category")));
        return VIEW;
    }
}
