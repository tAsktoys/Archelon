/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.io.Serializable;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * An entity of archelon's user.
 *
 * <code>User</code> is an immutable class. If you want to change member in this
 * class, call "with" method and get new instance.
 *
 * @author mikan
 */
public final class User implements Serializable {

    /**
     * Archelon-unique user id.
     */
    private final long id;

    /**
     * Name of user.
     */
    private final String name;

    /**
     * E-maill address.
     */
    private final String email;

    /**
     * Hashed password.
     */
    private final String password;
    
    /**
     * Profile text.
     */
    private final String profile;
    
    /**
     * Birth date. Format is yyyyMMdd.
     */
    private final String birthdate;
    
    /**
     * User group. e.g. schools, companies, and associations.
     */
    private final String group;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.profile = builder.profile;
        this.birthdate = builder.birthdate;
        this.group = builder.group;
    }

    /**
     * Get user id.
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getProfile() {
        return profile;
    }

    public User withName(String name) {
        Builder builder = new Builder(this);
        builder.name(name);
        return builder.build();
    }

    public User withEmail(String email) {
        Builder builder = new Builder(this);
        builder.email(email);
        return builder.build();
    }

    public User withPassword(String password) {
        Builder builder = new Builder(this);
        builder.password(password);
        return builder.build();
    }

    /**
     * Build user object.
     *
     * @see Effective Java Second Edition Item 2
     */
    public static class Builder implements Serializable {

        private static final long ILLEGAL_ID = -1;

        private long id = ILLEGAL_ID;
        private String name = null;
        private String email = null;
        private String password = null;
        private String profile = null;
        private String birthdate = null;
        private String group = null;

        public Builder() {
            // do nothing
        }

        public Builder(User user) {
            this.id = user.id;
            this.name = user.name;
            this.email = user.email;
            this.password = user.password;
            this.profile = user.profile;
            this.birthdate = user.birthdate;
            this.group = user.group;
        }

        /**
         * Set user id.
         *
         * @param id user id
         * @throws IllegalArgumentException If specify illegal value
         */
        public void id(long id) {
            if (id < 0) {
                throw new IllegalArgumentException("negative value specified.");
            }
            // TODO: check duplicate id here.
            this.id = id;
        }

        /**
         * Set user name.
         *
         * @param name user name
         * @throws NullPointerException If specify null
         */
        public void name(String name) {
            if (name == null) {
                throw new NullPointerException("name is null.");
            }
            this.name = name;
        }

        /**
         * Set e-mail address.
         *
         * @param email e-mail address
         * @throws NullPointerException If specify null
         * @throws IllegalArgumentException If specify illegal format
         * @see InternetAddress#validate()
         */
        public void email(String email) {
            if (email == null) {
                throw new NullPointerException("email is null.");
            }
            if (!isValidEmailAddress(email)) {
                throw new IllegalArgumentException("email not valid.");
            }
            this.email = email;
        }

        /**
         * Set user password.
         *
         * @param password user password
         * @throws NullPointerException If specify null
         * @throws IllegalArgumentException If specify empty string
         */
        public void password(String password) {
            if (password == null) {
                throw new NullPointerException("password is null.");
            }
            if (password.isEmpty()) {
                throw new IllegalArgumentException();
            }
            this.password = password;
        }

        /**
         * Build user object.
         *
         * @return {@link User} object.
         * @throws IllegalStateException If not specify required information(s).
         */
        public User build() {
            if (id == ILLEGAL_ID) {
                throw new IllegalStateException("id not specified.");
            }
            if (name == null) {
                throw new IllegalStateException("name not specified.");
            }
            if (email == null) {
                throw new IllegalStateException("email not specified.");
            }
            if (password == null) {
                throw new IllegalStateException("password not specified.");
            }
            return new User(this);
        }

        /**
         * Validate format of e-mail address.
         *
         * @param email e-mail address
         * @return <code>true</code> if email is valid, <code>false</code>
         * otherwise
         * @see InternetAddress#validate()
         */
        private boolean isValidEmailAddress(String email) {
            try {
                InternetAddress emailAddr = new InternetAddress(email);
                emailAddr.validate();
                return true;
            } catch (AddressException ex) {
                return false;
            }
        }
    }
}
