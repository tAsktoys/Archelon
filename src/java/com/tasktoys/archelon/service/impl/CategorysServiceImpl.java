/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.dao.CategorysDao;
import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Categorys;
import com.tasktoys.archelon.service.CategorysService;
import java.util.List;
/**
 *
 * @author Yuichiro
 */
public class CategorysServiceImpl implements CategorysService {
 
    
    private final CategorysDao categorysDao = new CategorysDao();
    
    @Override
    public Categorys getMainCategorys() {
        return categorysDao.findMainCategorys();
    }
    
    @Override
    public Categorys getSubCategorys(String category) {
        return categorysDao.findSubCategorys(category);
    }
    
    public List<String> getMainCategoryList() {
        return categorysDao.findMainCategoryList();
    }
    
    public List<String> getSubCategoryList(String category) {
        return categorysDao.findSubCategoryList(category);
    }
}
