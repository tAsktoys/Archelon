/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.data.entity;

/**
 *
 * @author Yuichiro
 */
public class Categorys {
    
    private String main_category;
    private String sub_category;
    
    public Categorys() {
        
    }
    
    public void setMainCategory(String main_category) {
        this.main_category = main_category;
    }
    
    public String getMainCategory() {
        return this.main_category;
    }
    
    public void setSubCategory(String sub_category) {
        this.sub_category = sub_category;
    }
    
    public String getSubCategory(String sub_category) {
        return this.sub_category;
    }
}
