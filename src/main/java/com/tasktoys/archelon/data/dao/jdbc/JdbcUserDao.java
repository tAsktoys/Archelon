/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.UserDao;
import com.tasktoys.archelon.data.entity.OAuthAccount;
import com.tasktoys.archelon.data.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * A data access object for user information.
 *
 * @author mikan
 * @author YuichiroSato
 * @since 0.1
 * @see User
 */
@Repository
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private static final String USER_TABLE = "user";

    /**
     * Definision columns of user table. Each value is ordered by the same
     * oreder in the table.
     */
    private enum Column {

        ID, STATE, NAME, EMAIL, PASSWORD, DESCRIPTION, BIRTHDATE, LOCATION,
        AFFILIATE, URL, TWITTER_ID, TWITTER_TOKEN, TWITTER_SECRET, FACEBOOK_ID,
        FACEBOOK_TOKEN, FACEBOOK_SECRET;

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
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findUserByName(String name) {
        String sql = "select * from " + USER_TABLE + " where name=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User findUserByID(Long id) {
        String sql = "select * from " + USER_TABLE + " where id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertUser(User user) {
        String sql = "insert into " + USER_TABLE + encodeColumnToSet();
        jdbcTemplate.update(sql, user.toObject());
    }
    
    @Override
    public void updateUser(User user) {
        String sql = "update " + USER_TABLE + encodeColumnToSet()
                + " where id=" + user.getId();
        jdbcTemplate.update(sql, user.toObject());
    }

    /**
     * Row mapper for user entity.
     */
    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet result, int row) throws SQLException {
            User.Builder builder = new User.Builder();
            builder.id(result.getLong(Column.ID.toString()));
            builder.state(result.getInt(Column.STATE.toString()));
            builder.name(result.getString(Column.NAME.toString()));
            builder.email(result.getString(Column.EMAIL.toString()));
            builder.password(result.getString(Column.PASSWORD.toString()));
            builder.description(result.getString(Column.DESCRIPTION.toString()));
            builder.birthdate(result.getDate(Column.BIRTHDATE.toString()));
            builder.location(result.getString(Column.LOCATION.toString()));
            builder.affiliate(result.getString(Column.AFFILIATE.toString()));
            builder.url(result.getString(Column.URL.toString()));
            builder.twitterId(result.getString(Column.TWITTER_ID.toString()));
            builder.twitterToken(result.getString(Column.TWITTER_TOKEN.toString()));
            builder.twitterSecret(result.getString(Column.TWITTER_SECRET.toString()));
            builder.facebookId(result.getString(Column.FACEBOOK_ID.toString()));
            builder.facebookToken(result.getString(Column.FACEBOOK_TOKEN.toString()));
            builder.facebookSecret(result.getString(Column.FACEBOOK_SECRET.toString()));
            return builder.build();
        }
    }

    private String encodeColumnToSet() {
        String sql = " set ";
        for (Column c : Column.values()) {
            sql += c.toString() + "=?,";
        }
        return sql.substring(0, sql.length() - 1);
    }
}
