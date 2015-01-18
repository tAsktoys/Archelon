/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

import java.util.List;
import java.util.Map;
/**
 *
 * @author YuichiroSato
 */
public interface CategoryService {
    
    public Map<String, List<Map<String, String>>> createMainCategories(String name);
    public Map<String, List<Map<String, String>>> createSubCategories(String name, int mainId);
}
