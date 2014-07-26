/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.CategoriesDao;
import com.tasktoys.archelon.service.CategorysService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yuichiro
 */
@Service
public class CategorysServiceImpl implements CategorysService {
 
    @Autowired
    private CategoriesDao categorysDao;
    
    @Override
    public List<String> getMainCategoryList() {
        return categorysDao.findMainCategoryList();
    }
    
    @Override
    public List<String> getSubCategoryList(String category) {
        return categorysDao.findSubCategoryList(category);
    }
}
