/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import com.tasktoys.archelon.data.entity.Discussion.Builder;
import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
public class DiscussionTest {

    @Test
    public void testBuild() {
        Discussion discussion = new Builder()
                .id(1)
                .state(Discussion.State.ACTIVE)
                .authorId(2)
                .categoryId(3)
                .createTime(new Timestamp(3))
                .updateTime(new Timestamp(3))
                .subject("hoge")
                .participants(5)
                .posts(6)
                .build();

        assertEquals(1, discussion.getId());
        assertEquals(2, discussion.getAuthorId());
        assertEquals(3, discussion.getCategoryId());
        assertEquals(new Timestamp(3), discussion.getCreateTime());
        assertEquals(new Timestamp(3), discussion.getUpdateTime());
        assertEquals("hoge", discussion.getSubject());
        assertEquals(5, discussion.getParticipants());
        assertEquals(6, discussion.getPosts());
    }

    @Test
    public void testBuildForInsert() {
        Discussion discussion = new Builder()
                .authorId(2)
                .categoryId(3)
                .subject("hoge")
                .buildForInsert();

        assertEquals(Discussion.ILLEGAL_ID, discussion.getId());
        assertEquals(2, discussion.getAuthorId());
        assertEquals(3, discussion.getCategoryId());
        assertEquals(discussion.getCreateTime(), discussion.getUpdateTime());
        assertEquals("hoge", discussion.getSubject());
        assertEquals(1, discussion.getParticipants());
        assertEquals(1, discussion.getPosts());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIlliegalId() {
        new Builder().id(-4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIlliegalAuthorId() {
        new Builder().authorId(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIlliegalCategoryId() {
        new Builder().categoryId(-4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIlliegalState() {
        new Builder().state(100000);
    }

    @Test(expected = NullPointerException.class)
    public void testNullState() {
        new Builder().state(null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullCreateTime() {
        new Builder().createTime(null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullUpdateTime() {
        new Builder().updateTime(null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullSubject() {
        new Builder().subject(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild1() {
        new Builder().build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild2() {
        new Builder().id(1).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild3() {
        new Builder()
                .id(1)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild4() {
        new Builder()
                .id(1)
                .authorId(2)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild5() {
        new Builder()
                .id(1)
                .authorId(2)
                .categoryId(3)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild6() {
        new Builder()
                .id(1)
                .authorId(2)
                .categoryId(3)
                .state(Discussion.State.ACTIVE)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild7() {
        new Builder()
                .id(1)
                .authorId(2)
                .categoryId(3)
                .state(Discussion.State.ACTIVE)
                .createTime(new Timestamp(3))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild8() {
        new Builder()
                .id(1)
                .authorId(2)
                .categoryId(3)
                .state(Discussion.State.ACTIVE)
                .createTime(new Timestamp(3))
                .updateTime(new Timestamp(4))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild9() {
        new Builder()
                .id(1)
                .authorId(2)
                .categoryId(3)
                .state(Discussion.State.ACTIVE)
                .createTime(new Timestamp(3))
                .updateTime(new Timestamp(4))
                .subject("hoge")
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuild10() {
        new Builder()
                .id(1)
                .authorId(2)
                .categoryId(3)
                .state(Discussion.State.ACTIVE)
                .createTime(new Timestamp(3))
                .updateTime(new Timestamp(4))
                .subject("hoge")
                .participants(1)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildForInsert1() {
        new Builder().buildForInsert();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildForInsert2() {
        new Builder().id(1).buildForInsert();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildForInsert3() {
        new Builder()
                .id(1)
                .buildForInsert();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildForInsert4() {
        new Builder()
                .id(1)
                .authorId(2)
                .buildForInsert();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildForInsert5() {
        new Builder()
                .id(1)
                .authorId(2)
                .categoryId(3)
                .buildForInsert();
    }
}
