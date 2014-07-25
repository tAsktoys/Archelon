/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Categorys;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Yuichiro
 */
public class CategorysDao {
    
    public Categorys findMainCategorys() {
        return new Categorys();
    }
    
    public Categorys findSubCategorys(String category) {
        return new Categorys();
    }
    
    public List<String> findMainCategoryList() {
        List<String> list = new ArrayList<>();
        list.add("math");
        list.add("sience");
        list.add("history");
        return list;
    }
    
    public List<String> findSubCategoryList(String category) {
        List<String> list = new ArrayList<>();
        list.add(category + "1");
        list.add(category + "2");
        list.add(category + "3");
        return list;
    }
}
