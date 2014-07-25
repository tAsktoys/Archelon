/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Categorys;
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
}
