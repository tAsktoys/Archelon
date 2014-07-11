/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.impl;

import com.tasktoys.archelon.data.dao.UserDao;
import com.tasktoys.archelon.data.dao.UserSearchKey;
import com.tasktoys.archelon.data.entity.User;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * A data access object for user information.
 *
 * @author mikan
 */
@Repository
public class UserDaoImpl implements UserDao, Serializable {

    private JdbcTemplate jdbcTemplate;

    private static final String USER_TABLE = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findUser(UserSearchKey searchKey) {
        String sql = "select * from " + USER_TABLE + " ";
        String where = "";
        if (searchKey.getId() >= 0) {
            where += " and id = :id";
        }
        if (searchKey.getName() != null) {
            where += " and name = :name";
        }
        if (searchKey.getEmail() != null) {
            where += " and email = :email";
        }
        if (!where.isEmpty()) {
            sql += " where " + where.substring(4);
        }
        User user = jdbcTemplate.queryForObject(sql,
                new UserRowMapper(),
                new BeanPropertySqlParameterSource(searchKey));
        return user;
    }

    private static class UserRowMapper implements RowMapper<User>, Serializable {

        @Override
        public User mapRow(ResultSet result, int row) throws SQLException {
            User.Builder builder = new User.Builder();
            builder.id(result.getLong(COLUMN_ID));
            builder.name(result.getString(COLUMN_NAME));
            builder.email(result.getString(COLUMN_EMAIL));
            builder.password(result.getString(COLUMN_PASSWORD));
            return builder.build();
        }
    }
}
