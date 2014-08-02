/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.CategoryDao;
import com.tasktoys.archelon.data.entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
/**
 *
 * @author YuichiroSato
 */
@Repository
public class JdbcCategoryDao implements CategoryDao {
    
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
    
    private enum Table {
        category_main, category_sub, discussion
    }
    
    private enum Colom {
        id, name
    }
    
    @Override
    public Category findMainCategoryByID(int id) {
        String sql = "select * from " + Table.category_main.name() + " where id=" + id;
        try {
            return jdbcTemplate.queryForObject(sql, new CategoryRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Category> findMainCategories() {
        String sql = "select * from " + Table.category_main.name() + ";";
        return responseToCategoryList(jdbcTemplate.queryForList(sql));
    }
    
    @Override
    public List<Category> findSubCategories(int selected_id) {
        String sql = "select * from " + Table.category_sub.name() + " where main_id=" + selected_id;
        return responseToCategoryList(jdbcTemplate.queryForList(sql));
    }
    
    private List<Category> responseToCategoryList(List<Map<String, Object>> response) {
        List<Category> list = new ArrayList<>();
        for (Map<String, Object> map : response) {
            Integer id = (Integer)map.get(Colom.id.name());
            String name = (String)map.get(Colom.name.name());
            list.add(Category.builder.build(id, name));
        }
        return list;
    }
    
    private static class CategoryRowMapper implements RowMapper<Category> {
        
        @Override
        public Category mapRow(ResultSet result, int row) throws SQLException {
            int id = result.getInt(Colom.id.name());
            String name = result.getString(Colom.name.name());
            return Category.builder.build(id, name);
        }
    }
}
