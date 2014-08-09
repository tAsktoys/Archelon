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
        CATEGORY_MAIN, CATEGORY_SUB, DISCUSSION;
        
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    
    private enum Column {
        ID, NAME;
        
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    
    @Override
    public Category findMainCategoryByID(int id) {
        String sql = "select * from " + Table.CATEGORY_MAIN.toString() + " where id=" + id;
        try {
            return jdbcTemplate.queryForObject(sql, new CategoryRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Category> findMainCategories() {
        String sql = "select * from " + Table.CATEGORY_MAIN.toString() + ";";
        return responseToCategoryList(jdbcTemplate.queryForList(sql));
    }
    
    @Override
    public List<Category> findSubCategories(int selected_id) {
        String sql = "select * from " + Table.CATEGORY_SUB.toString() + " where main_id=" + selected_id;
        return responseToCategoryList(jdbcTemplate.queryForList(sql));
    }
    
    private List<Category> responseToCategoryList(List<Map<String, Object>> response) {
        List<Category> list = new ArrayList<>();
        for (Map<String, Object> map : response) {
            Integer id = (Integer)map.get(Column.ID.toString());
            String name = (String)map.get(Column.NAME.toString());
            list.add(Category.builder.build(id, name));
        }
        return list;
    }
    
    private static class CategoryRowMapper implements RowMapper<Category> {
        
        @Override
        public Category mapRow(ResultSet result, int row) throws SQLException {
            int id = result.getInt(Column.ID.toString());
            String name = result.getString(Column.NAME.toString());
            return Category.builder.build(id, name);
        }
    }
}
