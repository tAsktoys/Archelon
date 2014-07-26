/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Categorys;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author Yuichiro
 */
public class CategoriesDao {
    
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Set data source. It invoke from Spring Framework.
     *
     * @param dataSource data source
     */
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
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
