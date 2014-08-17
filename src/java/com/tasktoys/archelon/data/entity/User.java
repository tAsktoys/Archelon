/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * An entity of archelon's user.
 *
 * <code>User</code> is an immutable class. If you want to change member in this
 * class, call "with" method and get new instance.
 *
 * @author mikan
 * @author YuichiroSato
 */
public final class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Archelon-unique user id.
     */
    private final long id;

    /**
     * State
     */
    private final State state;

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
     * Description text.
     */
    private final String description;

    /**
     * Birth date. Format is yyyyMMdd.
     */
    private final Date birthdate;

    /**
     * User place. e.g. locations, schools, companies, and associations.
     */
    private final String location;

    /**
     * Affiliate.
     */
    private final String affiliate;

    /**
     * URL.
     */
    private final String url;

    /**
     * Twitter account.
     */
    private final OAuthAccount twitter;

    /**
     * Facebook account.
     */
    private final OAuthAccount facebook;

    private User(Builder builder) {
        this.id = builder.id;
        this.state = builder.state;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.description = builder.description;
        this.birthdate = builder.birthdate;
        this.location = builder.location;
        this.affiliate = builder.affiliate;
        this.url = builder.url;
        this.twitter = builder.twitter;
        this.facebook = builder.facebook;
    }

    public enum State {

        ACTIVE, INACTIVE, BANNED;
    }

    public long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswrod() {
        return password;
    }

    public boolean isValidPasswordWithPlaneString(String planeString) {
        String sha256 = DigestUtils.sha256Hex(planeString);
        if (sha256 == null) {
            return false;
        }
        return sha256.equalsIgnoreCase(password);
    }

    public boolean isValidPasswordWithHash(String sha256) {
        if (sha256 == null) {
            return false;
        }
        return sha256.equalsIgnoreCase(password);
    }

    public String getDescription() {
        return description;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getLocation() {
        return location;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public String getUrl() {
        return url;
    }

    public OAuthAccount getTwitter() {
        return twitter;
    }

    public OAuthAccount getFacebook() {
        return facebook;
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
        builder.description(profile);
        return builder.build();
    }

    public User withPlace(String place) {
        Builder builder = new Builder(this);
        builder.location(place);
        return builder.build();
    }

    public Object[] toObject() {
        Object[] twitterobj = (twitter == null ? new Object[]{ null, null, null } : twitter.toObject());
        Object[] facebookobj = (facebook == null ? new Object[]{ null, null, null } : facebook.toObject()); 
        return new Object[]{
            (id == Builder.ILLEGAL_ID ? null : id), state.ordinal(), name,
            email, password, description, birthdate, location, affiliate, url,
            twitterobj[0], twitterobj[1], twitterobj[2],
            facebookobj[0], facebookobj[1], facebookobj[2]};
    }

    /**
     * Build user object.
     *
     * Consultation: Effective Java Second Edition Item 2
     */
    public static class Builder {

        private static final String DATE_FORMAT_STRING = "yyyyMMdd";
        public static final SimpleDateFormat DATE_FORMAT
                = new SimpleDateFormat(DATE_FORMAT_STRING);

        public static final long ILLEGAL_ID = -1;

        private long id = ILLEGAL_ID;
        private State state = null;
        private String name = null;
        private String email = null;
        private String password = null;
        private String description = null;
        private Date birthdate = null;
        private String location = null;
        private String affiliate = null;
        private String url = null;
        private OAuthAccount twitter = null;
        private OAuthAccount facebook = null;

        public Builder() {
            // do nothing
        }

        public Builder(User user) {
            if (user == null) {
                return;
            }
            this.id = user.id;
            this.state = user.state;
            this.name = user.name;
            this.email = user.email;
            this.password = user.password;
            this.description = user.description;
            this.birthdate = user.birthdate;
            this.location = user.location;
            this.affiliate = user.affiliate;
            this.url = user.url;
            this.twitter = user.twitter;
            this.facebook = user.facebook;
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
         * Set user state.
         *
         * @param stateValue state
         * @throws NullPointerException If specify null
         */
        public void state(int stateValue) {
            for (State s : State.values()) {
                if (stateValue == s.ordinal()) {
                    this.state = s;
                    return;
                }
            }
            throw new IllegalArgumentException("illegal state: " + stateValue);
        }

        /**
         * Set user state.
         *
         * @param state state
         */
        public void state(State state) {
            this.state = state;
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
            // TODO: length check
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
            // TODO: length check
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
            // TODO: length check
            this.password = password;
        }

        public void description(String description) {
            if (description == null) {
                return;
            }
            // TODO: length check
            this.description = description;
        }

        /**
         * Set birthdate.
         *
         * @param birthdate birthdate
         * @throws IllegalArgumentException If specify invalid date
         */
        public void birthdate(Date birthdate) {
            if (birthdate == null) {
                return;
            }
            this.birthdate = birthdate;
        }

        public void location(String place) {
            if (place == null) {
                return;
            }
            // TODO: length check
            this.location = place;
        }

        public void affiliate(String affiliate) {
            if (affiliate == null) {
                return;
            }
            this.affiliate = affiliate;
        }

        public void url(String url) {
            if (url == null) {
                return;
            }
            this.url = url;
        }

        public void twitterId(String id) {
            if (id == null) {
                return;
            }
            if (twitter == null) {
                twitter = new OAuthAccount();
            }
            twitter.setId(id);
        }

        public void twitterToken(String token) {
            if (token == null) {
                return;
            }
            if (twitter == null) {
                twitter = new OAuthAccount();
            }
            twitter.setAccessToken(token);
        }

        public void twitterSecret(String secret) {
            if (secret == null) {
                return;
            }
            if (twitter == null) {
                twitter = new OAuthAccount();
            }
            twitter.setAccessSecret(secret);
        }

        public void facebookId(String id) {
            if (id == null) {
                return;
            }
            if (facebook == null) {
                facebook = new OAuthAccount();
            }
            facebook.setId(id);
        }

        public void facebookToken(String token) {
            if (token == null) {
                return;
            }
            if (facebook == null) {
                facebook = new OAuthAccount();
            }
            facebook.setAccessToken(token);
        }

        public void facebookSecret(String secret) {
            if (secret == null) {
                return;
            }
            if (facebook == null) {
                facebook = new OAuthAccount();
            }
            facebook.setAccessSecret(secret);
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
            if (state == null) {
                throw new IllegalStateException("state not specified.");
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
            if (twitter != null) {
                if (!twitter.validate()) {
                    throw new IllegalStateException(
                            "twitter creation incomplete.");
                }
            }
            if (facebook != null) {
                if (facebook.validate()) {
                    throw new IllegalStateException(
                            "facebook creation incomplete.");
                }
            }
            return new User(this);
        }

        /**
         * Build user object without User id.
         *
         * @return {@link User} object.
         * @throws IllegalStateException If not specify required information(s).
         */
        public User buildForInsert() {
            if (id != ILLEGAL_ID) {
                throw new IllegalStateException("id is specified.");
            }
            if (state == null) {
                throw new IllegalStateException("state not specified.");
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
        public boolean isValidEmailAddress(String email) {
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
