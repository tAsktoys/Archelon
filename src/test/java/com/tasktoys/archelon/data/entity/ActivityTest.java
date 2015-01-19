/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import com.tasktoys.archelon.data.entity.Activity.ActivityType;
import com.tasktoys.archelon.data.entity.Activity.Builder;
import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
public class ActivityTest {

    public void testBuild() {
        Builder build = new Builder();
        Activity activity = build.id(1)
                .activityType(1)
                .userId(2)
                .createdTime(new Timestamp(3))
                .targetDiscussionId(new Long(4))
                .targetUserId(new Long(5))
                .targetDiscussionConcentId("012345678901234567890123")
                .targetPost(new Integer(6))
                .build();

        assertEquals((long) 1, activity.getId());
        assertEquals(1, activity.getActivityType().ordinal());
        assertEquals((long) 2, activity.getUserId());
        assertEquals(new Timestamp(3), activity.getUserId());
        assertEquals(new Long(4), activity.getTargetDiscussionId());
        assertEquals("012345678901234567890123", activity.getTargetDiscussionContentId());
        assertEquals(new Integer(6), activity.getTargetPost());
    }

    public void testBuildForInsert() {
        Builder build = new Builder();
        Activity activity = build.activityType(ActivityType.CLOSE_DISCUSSION)
                .userId(2)
                .createdTime(new Timestamp(3))
                .targetDiscussionId(new Long(4))
                .targetUserId(new Long(5))
                .targetDiscussionConcentId("012345678901234567890123")
                .targetPost(new Integer(6))
                .buildForInsert();

        assertEquals(Activity.ILLEGAL_ID, activity.getId());
        assertEquals(ActivityType.CLOSE_DISCUSSION, activity.getActivityType());
        assertEquals((long) 2, activity.getUserId());
        assertEquals(new Timestamp(3), activity.getUserId());
        assertEquals(new Long(4), activity.getTargetDiscussionId());
        assertEquals("012345678901234567890123", activity.getTargetDiscussionContentId());
        assertEquals(new Integer(6), activity.getTargetPost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalId() {
        new Builder().id(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalActivityType() {
        new Builder().activityType(-2);
    }

    @Test(expected = NullPointerException.class)
    public void testNullActivityType() {
        new Builder().activityType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalUserId() {
        new Builder().userId(-2);
    }

    @Test(expected = NullPointerException.class)
    public void testNullCreatedTime() {
        new Builder().createdTime(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortTargetDiscussionContentId() {
        new Builder().targetDiscussionConcentId("12345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongTargetDiscussionContentId() {
        new Builder().targetDiscussionConcentId("0123456789abcdef0123456789abcdef");
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildWithIllegalId() {
        new Builder().build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildWithIllegalUserId() {
        new Builder().id(1).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildWithNullActivityType() {
        new Builder().id(1).userId(1).createdTime(new Timestamp(2)).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildWithNullCreatedTime() {
        new Builder().id(1).userId(1).activityType(ActivityType.SOLVE_DISCUSSION).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildForInsertWithSpecifiedId() {
        new Builder().id(1).buildForInsert();
    }
}
