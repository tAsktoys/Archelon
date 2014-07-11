/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * User place. e.g. locations, schools, companies, and associations.
     */
    private final String place;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.profile = builder.profile;
        this.birthdate = builder.birthdate;
        this.place = builder.place;
    }

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
    
    public Date getBirthdate() {
        try {
            if (birthdate != null)
                return new SimpleDateFormat(Builder.dateFormat).parse(birthdate);
        } catch (ParseException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public String getPlace() {
        return place;
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
    
    public User withProfile(String profile) {
        Builder builder = new Builder(this);
        builder.profile(profile);
        return builder.build();
    }
    
    public User withPlace(String place) {
        Builder builder = new Builder(this);
        builder.place(place);
        return builder.build();
    }

    /**
     * Build user object.
     *
     * Consultation: Effective Java Second Edition Item 2
     */
    public static class Builder {

        private static final long ILLEGAL_ID = -1;
        private static final String dateFormat = "yyyyMMdd";

        private long id = ILLEGAL_ID;
        private String name = null;
        private String email = null;
        private String password = null;
        private String profile = null;
        private String birthdate = null;
        private String place = null;

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
            this.place = user.place;
        }

        /**
         * Set user id.
         *
         * @param id user id
         * @throws IllegalArgumentException If specify illegal value
         */
        public void id(long id) {
            if (id < 0) {
                throw new IllegalArgumentException("wrong id: " + id);
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
                throw new IllegalArgumentException("wrong email.");
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
        
        public void profile(String profile) {
            if (profile == null)
                return;
            this.profile = profile;
        }
        
        /**
         * Set birthdate.
         * 
         * @param birthdate birthdate
         * @throws IllegalArgumentException If specify invalid date
         */
        public void birthdate(String birthdate) {
            if (birthdate == null)
                return;
            if (!isValidDate(birthdate))
                throw new IllegalArgumentException("wring date: " + birthdate);
            this.birthdate = birthdate;
        }
        
        public void place(String place) {
            if (place == null)
                return;
            this.place = place;
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
        
        private boolean isValidDate(String date) {
            if (date == null)
                throw new NullPointerException("date is null.");
            try {
                new SimpleDateFormat(Builder.dateFormat).parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
    }
}
