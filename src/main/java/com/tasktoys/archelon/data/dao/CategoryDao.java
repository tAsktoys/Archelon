/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Category;
import java.util.List;

/**
 * Interface of category data operations.
 *
 * @author mikan
 * @since 0.1
 */
public interface CategoryDao {
    
    /**
     * Find category entity by main category id.
     * 
     * @param id main category id
     * @return main category's entity, or <code>null</code> if not found.
     */
    public Category findMainCategoryByID(int id);
    
    /**
     * Find list of main categories.
     * 
     * @return list of main categories, or empty list if not found categories.
     */
    public List<Category> findMainCategories();
    
    /**
     * Find list of sub categories by specified main category.
     * 
     * @param mainCategoryId main category's id
     * @return list of sub categories, or empty list if not found categories.
     */
    public List<Category> findSubCategories(int mainCategoryId);
}
