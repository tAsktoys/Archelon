/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.dao;

import com.tasktoys.archelon.data.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * A data access object for user information.
 *
 * @author mikan
 * @see User
 */
@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    private static final String USER_TABLE = "user";

    /**
     * Definision columns of user table.
     */
    private enum Column {

        ID, NAME, EMAIL, PASSWORD, PROFILE, BIRTHDATE, PLACE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Set data source. It invoke from Spring Framework.
     * 
     * @param dataSource data source
     */
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Find user record by name.
     * 
     * @param name name of user
     * @return user, or <code>null</code> if not found.
     */
    public User findUserByName(String name) {
        String sql = "select * from " + USER_TABLE + " where name=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Row mapper for user entity.
     */
    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet result, int row) throws SQLException {
            User.Builder builder = new User.Builder();
            builder.id(result.getLong(Column.ID.toString()));
            builder.name(result.getString(Column.NAME.toString()));
            builder.email(result.getString(Column.EMAIL.toString()));
            builder.password(result.getString(Column.PASSWORD.toString()));
            builder.profile(result.getString(Column.PROFILE.toString()));
            builder.birthdate(result.getString(Column.BIRTHDATE.toString()));
            builder.place(result.getString(Column.PLACE.toString()));
            return builder.build();
        }
    }
}
