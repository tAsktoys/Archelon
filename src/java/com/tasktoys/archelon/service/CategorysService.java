/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.Category;
import com.tasktoys.archelon.data.entity.Categorys;
/**
 *
 * @author Yuichiro
 */
public interface CategorysService {
    
    public Categorys getMainCategorys();
    public Categorys getSubCategorys(String category);
}
