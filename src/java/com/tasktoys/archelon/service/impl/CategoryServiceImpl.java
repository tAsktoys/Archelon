/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.CategoryDao;
import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YuichiroSato
 */
@Service
public class CategoryServiceImpl implements CategoryService {
 
    @Autowired
    private CategoryDao categoryDao;
    
    @Override
    public List<Category> getMainCategoryList() {
        return categoryDao.findMainCategoryList();
    }
    
    @Override
    public List<Category> getSubCategoryList(int selected_id) {
        return categoryDao.findSubCategoryList(selected_id);
    }
}
