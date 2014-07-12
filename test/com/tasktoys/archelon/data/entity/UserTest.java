/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.data.entity;

import com.tasktoys.archelon.data.entity.User.Builder;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
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
    
    public void testBuilder_fullDataCase() {
        long id = 1;
        String name = "password";
        String email = "test@test.com";
        String password = "password";
        String profile = "This is sample profile.";
        String birthdate = "20141231";
        String place = "JAIST";
        
        Builder builder = new Builder();
        builder.id(id);
        builder.name(name);
        builder.email(email);
        builder.password(password);
        builder.profile(profile);
        builder.birthdate(birthdate);
        builder.place(place);
        User user = builder.build();
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(profile, user.getProfile());
        assertEquals(birthdate, user.getBirthdate());
        assertEquals(place, user.getPlace());
    }
    
    public void testBuilder_mustOnlyCase() {
        long id = 1;
        String name = "password";
        String email = "test@test.com";
        String password = "password";
        
        Builder builder = new Builder();
        builder.id(id);
        builder.name(name);
        builder.email(email);
        builder.password(password);
        User user = builder.build();
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertNull(user.getProfile());
        assertNull(user.getBirthdate());
        assertNull(user.getPlace());
    }
    
    /**
     * All empty case.
     */
    @Test(expected=IllegalStateException.class)
    public void testBuilderBuild_emptyCase() {
        new Builder().build();
    }
    
    @Test
    public void testBuilderId_maxValueCase() {
        Builder builder = new Builder();
        builder.id(Long.MAX_VALUE);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderId_minusValueCase() {
        Builder builder = new Builder();
        builder.id(-1);
    }
    
    @Test(expected=NullPointerException.class)
    public void testBuilderName_nullCase() {
        new Builder().name(null);
    }
    
    @Test(expected=NullPointerException.class)
    public void testBuilderEmail_nullCase() {
        new Builder().email(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase1() {
        new Builder().email("");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase2() {
        new Builder().email("test");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase3() {
        new Builder().email("test@");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase4() {
        new Builder().email("@test");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase5() {
        new Builder().email("@test.com");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderEmail_invalidCase6() {
        new Builder().email("@test@test.com");
    }
    
    @Test(expected=NullPointerException.class)
    public void testBuilderPassword_nullCase() {
        new Builder().password(null);
    }
    
    @Test // want value, not thrown NPE
    public void testBuilderProfile_nullCase() {
        new Builder().profile(null);
    }
    
    @Test // want value, not thrown NPE
    public void testBuilderBirthdate_nullCase() {
        new Builder().birthdate(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderBirthdate_invalidCase1() {
        new Builder().birthdate("1234567");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderBirthdate_invalidCase2() {
        new Builder().birthdate("123456789");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBuilderBirthdate_invalidCase3() {
        new Builder().birthdate("abcdefgh");
    }
    
    @Test // want value, not thrown NPE
    public void testBuilderPlace_nullCase() {
        new Builder().place(null);
    }
}
