/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

import java.util.List;
/**
 *
 * @author Yuichiro
 */
public interface CategorysService {
    
    public List<String> getMainCategoryList();
    public List<String> getSubCategoryList(String category);
}
