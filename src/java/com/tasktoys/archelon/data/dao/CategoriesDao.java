/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.Categorys;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Yuichiro
 */
@Repository
public class CategoriesDao {
    
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Set data source. It invoke from Spring Framework.
     *
     * @param dataSource data source
     */
    @Autowired
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
