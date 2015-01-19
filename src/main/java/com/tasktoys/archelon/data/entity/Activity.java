/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.sql.Timestamp;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
public class Activity {

    public static final long ILLEGAL_ID = -1;
    public static final long ILLEGAL_USER_ID = User.ILLEGAL_ID;

    private final long id;
    private final ActivityType activityType;
    private final long userId;
    private final Timestamp createdTime;
    private final Long targetDiscussionId;
    private final Long targetUserId;
    private final String targetDiscussionContentId;
    private final Integer targetPost;

    public enum ActivityType {

        CREATE_DISCUSSION, SOLVE_DISCUSSION, CLOSE_DISCUSSION;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private Activity(Builder builder) {
        this.id = builder.id;
        this.activityType = builder.activityType;
        this.userId = builder.userId;
        this.createdTime = builder.createdTime;
        this.targetDiscussionId = builder.targetDiscussionId;
        this.targetUserId = builder.targetUserId;
        this.targetDiscussionContentId = builder.targetDiscussionContentId;
        this.targetPost = builder.targetPost;
    }

    public long getId() {
        return id;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public long getUserId() {
        return userId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public Long getTargetDiscussionId() {
        return targetDiscussionId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public String getTargetDiscussionContentId() {
        return targetDiscussionContentId;
    }

    public Integer getTargetPost() {
        return targetPost;
    }

    public static class Builder {

        private long id = ILLEGAL_ID;
        private ActivityType activityType;
        private long userId = ILLEGAL_USER_ID;
        private Timestamp createdTime;
        private Long targetDiscussionId;
        private Long targetUserId;
        private String targetDiscussionContentId;
        private int targetPost;

        public Builder() {

        }

        public Builder id(long id) {
            if (id <= ILLEGAL_ID) {
                throw new IllegalArgumentException("illegal id: " + id);
            }
            this.id = id;
            return this;
        }

        public Builder activityType(int activityType) {
            for (ActivityType type : ActivityType.values()) {
                if (activityType == type.ordinal()) {
                    this.activityType = type;
                    return this;
                }
            }
            throw new IllegalArgumentException("illegal activity type : " + activityType);
        }

        public Builder activityType(ActivityType activityType) {
            if (activityType == null) {
                throw new NullPointerException("activityTime is null");
            }
            this.activityType = activityType;
            return this;
        }

        public Builder userId(long userId) {
            if (userId <= ILLEGAL_USER_ID) {
                throw new IllegalArgumentException("illegal usre id: " + userId);
            }
            this.userId = userId;
            return this;
        }

        public Builder createdTime(Timestamp createdTime) {
            if (createdTime == null) {
                throw new NullPointerException("createdTime is null");
            }
            this.createdTime = createdTime;
            return this;
        }

        public Builder targetDiscussionId(Long targetDiscussionId) {
            this.targetDiscussionId = targetDiscussionId;
            return this;
        }

        public Builder targetUserId(Long targetUserId) {
            this.targetUserId = targetUserId;
            return this;
        }

        public Builder targetDiscussionConcentId(String targetDiscussionContentId) {
            if (targetDiscussionContentId != null && targetDiscussionContentId.length() != 24) {
                throw new IllegalArgumentException("target discussion content id is wrong: "
                        + targetDiscussionContentId);
            }
            this.targetDiscussionContentId = targetDiscussionContentId;
            return this;
        }

        public Builder targetPost(Integer targetPost) {
            this.targetPost = targetPost;
            return this;
        }

        public Activity build() {
            if (this.id <= ILLEGAL_ID) {
                throw new IllegalStateException("illegal id: " + id);
            }
            if (this.userId <= ILLEGAL_USER_ID) {
                throw new IllegalStateException("illegal user id: " + userId);
            }
            if (this.activityType == null) {
                throw new IllegalStateException("activity type is null");
            }
            if (this.createdTime == null) {
                throw new IllegalStateException("created time is null");
            }
            return new Activity(this);
        }

        public Activity buildForInsert() {
            if (this.id != ILLEGAL_ID) {
                throw new IllegalStateException("id is specified: " + id);
            }
            if (this.userId <= ILLEGAL_USER_ID) {
                throw new IllegalStateException("illegal user id: " + userId);
            }
            if (this.activityType == null) {
                throw new IllegalStateException("activity type is null");
            }
            if (this.createdTime == null) {
                throw new IllegalStateException("created time is null");
            }
            return new Activity(this);
        }
    }
}
