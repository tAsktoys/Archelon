/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.CategoryDao;
import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.service.CategoryService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    private static final String ID = "id";
    private static final String NAME = "name";
    
    @Override
    public Map<String, List<Map<String, String>>> createMainCategories(String name) {
        return Collections.singletonMap(name, toMapList(getMainCategoryList()));
    }

    @Override
    public Map<String, List<Map<String, String>>> createSubCategories(String name, int mainId) {
        return Collections.singletonMap(name, toMapList(getSubCategoryList(mainId)));
    }

    private List<Category> getMainCategoryList() {
        return categoryDao.findMainCategories();
    }
    
    private List<Category> getSubCategoryList(int selected_id) {
        return categoryDao.findSubCategories(selected_id);
    }
    
    private List<Map<String, String>> toMapList(List<Category> categories) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Category category : categories) {
            Map<String, String> map = new HashMap<>(2);
            map.put(ID, Integer.toString(category.getId()));
            map.put(NAME, category.getName());
            list.add(map);
        }
        return list;
    }
}
