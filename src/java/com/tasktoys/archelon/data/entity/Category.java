/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yuichiro
 */
public final class Category implements Serializable{
    
    private int id;
    private String name;
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));
        map.put("name", name);
        return map;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return this.id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public static class list {
        
        public static List<Map<String, String>> toMapList(List<Category> categories) {
            List<Map<String, String>> list = new ArrayList<>();
            for(Category category : categories) {
                list.add(category.toMap());
            }
            return list;
        }
    
        public static List<Map<String, String>> toMapList(List<Category> categories, int selected_id) {
            List<Map<String, String>> list = new ArrayList<>();
            for(Category category : categories) {
                if (category.getID() == selected_id) {
                    Map<String, String> map = category.toMap();
                    map.put("selected", "selected");
                    list.add(map);
                } else {
                    list.add(category.toMap());
                }
            }
            return list;
        }
    }
}
