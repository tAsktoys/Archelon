/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author YuichiroSato
 * @since 0.1
 */
public class Discussion implements Serializable {

    /**
     * Serial version 1.
     *
     * @since 0.2
     */
    private static final long serialVersionUID = 1L;

    public static final long ILLEGAL_ID = -1;
    public static final long ILLEGAL_AUTHOR_ID = User.ILLEGAL_ID;
    public static final int ILLEGAL_CATEGORY_ID = Category.ILLEGAL_ID;
    public static final int ILLEGAL_PARTICIPANTS = -1;
    public static final int ILLEGAL_POSTS = -1;

    public static final int DEFAULT_PARTICIPANTS = 1;
    public static final int DEFAULT_POSTS = 1;

    private final long id;
    private final long authorId;
    private final int categoryId;
    private final State state;
    private final Timestamp createTime;
    private final Timestamp updateTime;
    private final String subject;
    private final int participants;
    private final int posts;

    @Deprecated // 特定の DB 向けのデータ構造をここに持ち込まないでください。
    public enum Column {

        ID, AUTHOR_ID, CATEGORY_ID, STATE, CREATE_TIME, UPDATE_TIME, SUBJECT,
        PARTICIPANTS, POSTS;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum State {

        ACTIVE, INACTIVE, SOLVED, CLOSED;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private Discussion(Builder builder) {
        this.id = builder.id;
        this.authorId = builder.authorId;
        this.categoryId = builder.categoryId;
        this.state = builder.state;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.subject = builder.subject;
        this.participants = builder.participants;
        this.posts = builder.posts;
    }

    public Long getID() {
        return this.id;
    }

    public Long getAuthorID() {
        return this.authorId;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public State getState() {
        return this.state;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public String getSubject() {
        return this.subject;
    }

    public int getParticipants() {
        return this.participants;
    }

    public int getPosts() {
        return this.posts;
    }

    @Deprecated // 特定の DB 向けのデータ構造をここに持ち込まないでください。
    public Object[] toObject() {
        return new Object[]{
            (id == Discussion.ILLEGAL_ID ? null : id), (authorId == Discussion.ILLEGAL_AUTHOR_ID ? null : authorId),
            categoryId, state.ordinal(), createTime, updateTime,
            subject, participants, posts
        };
    }

    @Deprecated // 特定の DB 向けのデータ構造をここに持ち込まないでください。
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(Column.ID.toString(), String.valueOf(id));
        map.put(Column.AUTHOR_ID.toString(), String.valueOf(authorId));
        map.put(Column.CATEGORY_ID.toString(), String.valueOf(categoryId));
        map.put(Column.STATE.toString(), String.valueOf(state));
        map.put(Column.CREATE_TIME.toString(), createTime.toString());
        map.put(Column.UPDATE_TIME.toString(), updateTime.toString());
        map.put(Column.SUBJECT.toString(), subject);
        map.put(Column.PARTICIPANTS.toString(), String.valueOf(participants));
        map.put(Column.POSTS.toString(), String.valueOf(posts));
        return map;
    }

    public Map<String, String> replaceAuthorIDToAuthorName(String name) {
        Map<String, String> map = this.toMap();
        map.remove(Column.AUTHOR_ID.toString());
        map.put(Column.AUTHOR_ID.toString(), name);
        return map;
    }

    public static class Builder {

        private long id = ILLEGAL_ID;
        private long authorId = ILLEGAL_AUTHOR_ID;
        private int categoryId = ILLEGAL_CATEGORY_ID;
        private State state;
        private Timestamp createTime;
        private Timestamp updateTime;
        private String subject;
        private int participants = ILLEGAL_PARTICIPANTS;
        private int posts = ILLEGAL_POSTS;

        public Builder() {

        }

        public Builder id(long id) {
            if (id <= ILLEGAL_ID) {
                throw new IllegalArgumentException("illegal id: " + id);
            }
            this.id = id;
            return this;
        }

        public Builder authorID(long authorId) {
            if (authorId <= ILLEGAL_AUTHOR_ID) {
                throw new IllegalArgumentException("illegal author id: " + authorId);
            }
            this.authorId = authorId;
            return this;
        }

        public Builder categoryID(int categoryId) {
            if (categoryId <= ILLEGAL_CATEGORY_ID) {
                throw new IllegalArgumentException("illegal category id: " + categoryId);
            }
            this.categoryId = categoryId;
            return this;
        }

        public Builder state(int state) {
            for (State s : State.values()) {
                if (state == s.ordinal()) {
                    this.state = s;
                    return this;
                }
            }
            throw new IllegalArgumentException("illegal state: " + state);
        }

        public Builder state(State state) {
            if (state == null) {
                throw new NullPointerException("state is null.");
            }
            this.state = state;
            return this;
        }

        public Builder createTime(Timestamp createTime) {
            if (createTime == null) {
                throw new NullPointerException("create time is null.");
            }
            this.createTime = createTime;
            return this;
        }

        public Builder updateTime(Timestamp updateTime) {
            if (updateTime == null) {
                throw new NullPointerException("update time is null.");
            }
            this.updateTime = updateTime;
            return this;
        }

        public Builder subject(String subject) {
            if (subject == null) {
                throw new NullPointerException("subject is null.");
            }
            this.subject = subject;
            return this;
        }

        public Builder participants(int participants) {
            if (participants <= ILLEGAL_PARTICIPANTS) {
                throw new IllegalArgumentException("illegal participants: " + participants);
            }
            this.participants = participants;
            return this;
        }

        public Builder posts(int posts) {
            if (posts <= ILLEGAL_POSTS) {
                throw new IllegalArgumentException("illegal posts: " + posts);
            }
            this.posts = posts;
            return this;
        }

        public Discussion build() {
            if (id <= ILLEGAL_ID) {
                throw new IllegalStateException("id is not specified.");
            }
            if (authorId <= ILLEGAL_AUTHOR_ID) {
                throw new IllegalStateException("author is not specified.");
            }
            if (subject == null) {
                throw new IllegalStateException("Subject is null.");
            }
            if (categoryId <= ILLEGAL_CATEGORY_ID) {
                throw new IllegalStateException("category id is not specified.");
            }
            if (state == null) {
                throw new IllegalStateException("state is null.");
            }
            if (createTime == null) {
                throw new IllegalStateException("create time is null.");
            }
            if (updateTime == null) {
                throw new IllegalStateException("update time is null.");
            }
            if (participants <= ILLEGAL_PARTICIPANTS) {
                throw new IllegalStateException("participants is not specified.");
            }
            if (posts <= ILLEGAL_POSTS) {
                throw new IllegalStateException("posts is not specified.");
            }
            return new Discussion(this);
        }

        public Discussion buildForInsert() {
            if (id != ILLEGAL_ID) {
                throw new IllegalStateException("id is specified.");
            }
            if (authorId <= ILLEGAL_AUTHOR_ID) {
                throw new IllegalStateException("author is not specified.");
            }
            if (subject == null) {
                throw new IllegalStateException("Subject is null.");
            }
            if (categoryId <= ILLEGAL_CATEGORY_ID) {
                throw new IllegalStateException("category id is not specified.");
            }

            this.state(State.ACTIVE);
            long unixtime = System.currentTimeMillis();
            this.createTime(new Timestamp(unixtime));
            this.updateTime(new Timestamp(unixtime));
            this.participants(DEFAULT_PARTICIPANTS);
            this.posts(DEFAULT_POSTS);
            return new Discussion(this);
        }
    }
}
