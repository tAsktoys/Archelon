/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import com.tasktoys.archelon.data.entity.User.Builder;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test code for {@link User} and {@link User.Builder}.
 *
 * @author mikan
 */
public class UserTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBuilder_fullDataCase() {
        long id = 1;
        String name = "password";
        String email = "test@test.com";
        String password = "password";
        String profile = "This is sample profile.";
        String birthdate = "2010-12-31";
        String location = "Nomi";
        String affiliate = "JAIST";
        User.State state = User.State.ACTIVE;

        User user = new Builder()
                .id(id)
                .name(name)
                .email(email)
                .plainTextPassword(password)
                .description(profile)
                .nonEmptyBirthdate(birthdate)
                .location(location)
                .affiliate(affiliate)
                .state(state)
                .build();
        
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(DigestUtils.sha256Hex(password), user.getPasswrod());
        assertEquals(profile, user.getDescription());
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, 11, 31, 00, 00, 00);
        calendar.set(Calendar.MILLISECOND, 0);
        
        assertEquals(calendar.getTime(), user.getBirthdate());
        assertEquals(location, user.getLocation());
        assertEquals(affiliate, user.getAffiliate());
        assertEquals(state, User.State.ACTIVE);
    }

    @Test
    public void testBuilder_mustOnlyCase() {
        long id = 1;
        String name = "password";
        String email = "test@test.com";
        String password = "password";
        User.State state = User.State.ACTIVE;

        User user = new Builder()
                .id(id)
                .name(name)
                .email(email)
                .plainTextPassword(password)
                .state(state)
                .build();
        
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(DigestUtils.sha256Hex(password), user.getPasswrod());
        assertNull(user.getDescription());
        assertNull(user.getBirthdate());
        assertNull(user.getLocation());
        assertNull(user.getAffiliate());
        assertNull(user.getUrl());
        assertNull(user.getTwitter());
        assertNull(user.getFacebook());
    }
    
    @Test
    public void testBuilderWithEmpty() {
        long id = 1;
        String name = "password";
        String email = "test@test.com";
        String password = "password";
        String profile = "";
        String birthdate = "";
        String location = "";
        String affiliate = "";
        User.State state = User.State.ACTIVE;

        User user = new Builder()
                .id(id)
                .name(name)
                .email(email)
                .plainTextPassword(password)
                .nonEmptyDescription(profile)
                .nonEmptyBirthdate(birthdate)
                .nonEmptyLocation(location)
                .nonEmptyAffiliate(affiliate)
                .state(state)
                .build();
        
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(DigestUtils.sha256Hex(password), user.getPasswrod());
        assertNull(user.getDescription());
        assertNull(user.getBirthdate());
        assertNull(user.getLocation());
        assertNull(user.getAffiliate());
        assertEquals(state, User.State.ACTIVE);
    }
    
    /**
     * All empty case.
     */
    @Test(expected = IllegalStateException.class)
    public void testBuilderBuild_emptyCase() {
        new Builder().build();
    }

    @Test
    public void testBuilderId_maxValueCase() {
        Builder builder = new Builder();
        builder.id(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderId_minusValueCase() {
        Builder builder = new Builder();
        builder.id(-1);
    }

    @Test(expected = NullPointerException.class)
    public void testBuilderName_nullCase() {
        new Builder().name(null);
    }

    @Test(expected = NullPointerException.class)
    public void testBuilderEmail_nullCase() {
        new Builder().email(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase1() {
        new Builder().email("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase2() {
        new Builder().email("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase3() {
        new Builder().email("test@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase4() {
        new Builder().email("@test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase5() {
        new Builder().email("@test.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase6() {
        new Builder().email("@test@test.com");
    }

    @Test(expected = NullPointerException.class)
    public void testBuilderPassword_nullCase() {
        new Builder().password(null);
    }

    @Test // want value, not thrown NPE
    public void testBuilderProfile_nullCase() {
        new Builder().description(null);
    }

    @Test // want value, not thrown NPE
    public void testBuilderBirthdate_nullCase() {
        new Builder().birthdate((Date) null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testHtmlDateFormatBirthdate_future() {
        new Builder().nonEmptyBirthdate("3000-11-11");
    }

        @Test (expected = IllegalArgumentException.class)
    public void testHtmlDateFormatBirthdate_wrongFormat() {
        new Builder().nonEmptyBirthdate("3000-1111");
    }

    @Test // want value, not thrown NPE
    public void testBuilderPlace_nullCase() {
        new Builder().location(null);
    }

//    private Date parseDate(String dateStr) {
//        try {
//            return Builder.DATE_FORMAT.parse(dateStr);
//        } catch (ParseException e) {
//            throw new AssertionError("invalid date format.");
//        }
//    }
}
