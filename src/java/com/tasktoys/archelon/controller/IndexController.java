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
    
    
    private final CategorysService categorysService = new CategorysServiceImpl();
    
    static final String VIEW = "index";
    private static final String MAIN_CATEGORY = "main_category";
    private static final String SUB_CATEGORY = "sub_category";

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute(MAIN_CATEGORY, categorysService.getMainCategorys());
        return VIEW;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String chooseCategory(@ModelAttribute("chosed") Categorys categorys, Model model) {
        model.addAttribute(MAIN_CATEGORY, categorysService.getMainCategorys());
        model.addAttribute(SUB_CATEGORY, categorysService.getSubCategorys(categorys.getMainCategory()));
        return VIEW;
    }
}
