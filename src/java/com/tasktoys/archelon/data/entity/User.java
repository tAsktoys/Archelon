/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static final long ILLEGAL_ID = -1;

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

        ACTIVE, INACTIVE, BANNED, WITHDREW;
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

    /**
     * Convert <code>User</code> propaties to Map.
     *
     * @return
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("state", state.toString());
        map.put("name", name);
        map.put("email", email);
        map.put("password", password);
        map.put("description", description);
        map.put("birthdate", (birthdate == null ? null : birthdate.toString()));
        map.put("location", location);
        map.put("affiliate", affiliate);
        map.put("url", url);
        map.put("twitterID", (twitter == null ? null : twitter.getId()));
        map.put("twitterToken", (twitter == null ? null : twitter.getAccessToken()));
        map.put("twitterSecret", (twitter == null ? null : twitter.getAccessSecret()));
        map.put("facebookID", (facebook == null ? null : facebook.getId()));
        map.put("facebookToken", (facebook == null ? null : facebook.getAccessToken()));
        map.put("facebookSecret", (facebook == null ? null : facebook.getAccessSecret()));
        return map;
    }

    /**
     * Convert <code>User</code> propaties to Map without information with
     * security risk.
     *
     * @return
     */
    public Map<String, String> toSecureMap() {
        Map<String, String> map = toMap();
        map.remove("password");
        map.remove("twitterToken");
        map.remove("twitterSecret");
        map.remove("facebookToken");
        map.remove("facebookSecret");
        return map;
    }

    public Object[] toObject() {
        Object[] twitterobj = (twitter == null ? new Object[]{null, null, null} : twitter.toObject());
        Object[] facebookobj = (facebook == null ? new Object[]{null, null, null} : facebook.toObject());
        return new Object[]{
            (id == ILLEGAL_ID ? null : id), state.ordinal(), name,
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
        private static final SimpleDateFormat DATE_FORMAT
                = new SimpleDateFormat(DATE_FORMAT_STRING);

        private static final int MAX_STRING_LENGTH = 64;
        private static final int MAX_TWITTER_ID_LENGTH = 20;
        private static final int MAX_FACEBOOK_ID_LENGTH = 64;

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
         * @return
         * @throws IllegalArgumentException If specify illegal value
         */
        public Builder id(long id) {
            if (id <= ILLEGAL_ID) {
                throw new IllegalArgumentException("id is illegal: " + id);
            }
            this.id = id;
            return this;
        }

        /**
         * Set user state.
         *
         * @param stateValue state
         * @return
         * @throws NullPointerException If specify null
         */
        public Builder state(int stateValue) {
            for (State s : State.values()) {
                if (stateValue == s.ordinal()) {
                    this.state = s;
                    return this;
                }
            }
            throw new IllegalArgumentException("state is illegal: " + stateValue);
        }

        /**
         * Set user state.
         *
         * @param state state
         * @return
         */
        public Builder state(State state) {
            if (state == null) {
                throw new NullPointerException("state is null.");
            }
            this.state = state;
            return this;
        }

        /**
         * Set user name.
         *
         * @param name user name
         * @return
         * @throws NullPointerException If specify null
         */
        public Builder name(String name) {
            if (name == null) {
                throw new NullPointerException("name is null.");
            }
            if (MAX_STRING_LENGTH < name.length()) {
                throw new IllegalArgumentException("name is too long: " + name);
            }
            this.name = name;
            return this;
        }

        /**
         * Set e-mail address.
         *
         * @param email e-mail address
         * @return
         * @throws NullPointerException If specify null
         * @throws IllegalArgumentException If specify illegal format
         * @see InternetAddress#validate()
         */
        public Builder email(String email) {
            if (email == null) {
                throw new NullPointerException("email is null.");
            }
            if (!isValidEmailAddress(email)) {
                throw new IllegalArgumentException("wrong email.");
            }
            if (MAX_STRING_LENGTH < email.length()) {
                throw new IllegalArgumentException("email is too long: " + email);
            }
            this.email = email;
            return this;
        }

        /**
         * Set user password.
         *
         * @param password user password
         * @return
         * @throws NullPointerException If specify null
         * @throws IllegalArgumentException If specify empty string
         */
        public Builder password(String password) {
            if (password == null) {
                throw new NullPointerException("password is null.");
            }
            if (password.isEmpty()) {
                throw new IllegalArgumentException("password is empty");
            }
            if (MAX_STRING_LENGTH < password.length()) {
                throw new IllegalArgumentException("password is too long: " + password);
            }
            this.password = password;
            return this;
        }

        public Builder plainTextPassword(String plainTextPassword) {
            return password(DigestUtils.sha256Hex(plainTextPassword));
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder nonEmptyDescription(String description) {
            if (description != null && !description.isEmpty()) {
                return description(description);
            }
            return this;
        }

        /**
         * Set birthdate.
         *
         * @param birthdate birthdate
         * @return
         */
        public Builder birthdate(Date birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder birthdate(String birthdate) {
            Date date;
            try {
                date = DATE_FORMAT.parse(birthdate);
            } catch (ParseException ex) {
                throw new IllegalArgumentException("birthdate format is wrong: " + birthdate);
            }
            if (isFuture(date)) {
                throw new IllegalArgumentException("birthdate is future: " + birthdate);
            }
            this.birthdate = date;
            return this;
        }

        public Builder htmlDateFormatBirthdate(String birthdate) {
            Date date = parseHtmlDate(birthdate);
            if (isFuture(date)) {
                throw new IllegalArgumentException("birthdate is future: " + date);
            }
            return birthdate(date);
        }

        public Builder nonEmptyBirthdate(String birthdate) {
            if (birthdate != null && !birthdate.isEmpty()) {
                return htmlDateFormatBirthdate(birthdate);
            }
            return this;
        }

        public Builder location(String location) {
            if (location != null && MAX_STRING_LENGTH < location.length()) {
                throw new IllegalArgumentException("location is too long: " + location);
            }
            this.location = location;
            return this;
        }

        public Builder nonEmptyLocation(String location) {
            if (location != null && !location.isEmpty()) {
                return location(location);
            }
            return this;
        }

        public Builder affiliate(String affiliate) {
            if (affiliate != null && MAX_STRING_LENGTH < affiliate.length()) {
                throw new IllegalArgumentException("affiliate is too long: " + affiliate);
            }
            this.affiliate = affiliate;
            return this;
        }

        public Builder nonEmptyAffiliate(String affiliate) {
            if (affiliate != null && !affiliate.isEmpty()) {
                return affiliate(affiliate);
            }
            return this;
        }

        public Builder url(String url) {
            if (url != null && MAX_STRING_LENGTH < url.length()) {
                throw new IllegalArgumentException("url is too long: " + url);
            }
            this.url = url;
            return this;
        }

        public Builder nonEmptyUrl(String url) {
            if (url != null && !url.isEmpty()) {
                return url(url);
            }
            return this;
        }

        public Builder twitterId(String id) {
            if (id == null) {
                return this;
            }
            if (twitter == null) {
                twitter = new OAuthAccount();
            }
            if (MAX_TWITTER_ID_LENGTH < id.length()) {
                throw new IllegalArgumentException("twitter id is too long: " + id);
            }
            twitter.setId(id);
            return this;
        }

        public Builder twitterToken(String token) {
            if (token == null) {
                return this;
            }
            if (twitter == null) {
                twitter = new OAuthAccount();
            }
            twitter.setAccessToken(token);
            return this;
        }

        public Builder twitterSecret(String secret) {
            if (secret == null) {
                return this;
            }
            if (twitter == null) {
                twitter = new OAuthAccount();
            }
            twitter.setAccessSecret(secret);
            return this;
        }

        public Builder facebookId(String id) {
            if (id == null) {
                return this;
            }
            if (facebook == null) {
                facebook = new OAuthAccount();
            }
            if (MAX_FACEBOOK_ID_LENGTH < id.length()) {
                throw new IllegalArgumentException("facebook id is too long: " + id);
            }
            facebook.setId(id);
            return this;
        }

        public Builder facebookToken(String token) {
            if (token == null) {
                return this;
            }
            if (facebook == null) {
                facebook = new OAuthAccount();
            }
            facebook.setAccessToken(token);
            return this;
        }

        public Builder facebookSecret(String secret) {
            if (secret == null) {
                return this;
            }
            if (facebook == null) {
                facebook = new OAuthAccount();
            }
            facebook.setAccessSecret(secret);
            return this;
        }

        /**
         * Build user object.
         *
         * @return {@link User} object.
         * @throws IllegalStateException If not specify required information(s).
         */
        public User build() {
            if (id <= ILLEGAL_ID) {
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
                if (!facebook.validate()) {
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
        public static boolean isValidEmailAddress(String email) {
            try {
                InternetAddress emailAddr = new InternetAddress(email);
                emailAddr.validate();
                return true;
            } catch (AddressException ex) {
                return false;
            }
        }
        
        public static boolean isHtmlDateFormat(String date) {
            try {
                String[] yyyy_mm_dd = date.split("-");
                Integer.parseInt(yyyy_mm_dd[0]);
                Integer.parseInt(yyyy_mm_dd[1]);
                Integer.parseInt(yyyy_mm_dd[2]);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return false;
            }
            return true;
        }
        
        private static Date parseHtmlDate(String date) {
            int year, month, day;
            try {
                String[] yyyy_mm_dd = date.split("-");
                year = Integer.parseInt(yyyy_mm_dd[0]);
                month = Integer.parseInt(yyyy_mm_dd[1]) - 1;
                day = Integer.parseInt(yyyy_mm_dd[2]);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("birthdate format is wrong: " + date);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
        
        public static boolean isFuture(String date) {
            return isFuture(parseHtmlDate(date));
        }

        public static boolean isFuture(Date date) {
            return date.compareTo(Calendar.getInstance().getTime()) > 0;
        }
    }
}
