/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.Category;
import java.util.List;
/**
 *
 * @author YuichiroSato
 */
public interface CategoryService {
    
    public List<Category> getMainCategoryList();
    public List<Category> getSubCategoryList(int selected_id);
}
