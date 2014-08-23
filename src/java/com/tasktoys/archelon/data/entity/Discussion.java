/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import com.tasktoys.archelon.data.dao.DiscussionContentDao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author YuichiroSato
 */
public class Discussion {

    private final long id;
    private final long authorID;
    private final int categoryID;
    private final State state;
    private final Timestamp createTime;
    private final Timestamp updateTime;
    private final String subject;
    private final int participants;
    private final int posts;
    
    @Autowired
    private DiscussionContentDao discussionContentDao;

    public enum Column {

        ID, AUTHOR_ID, CATEGORY_ID, STATE, CREATE_TIME, UPDATE_TIME, SUBJECT,
        PARTICIPANTS, POSTS;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum State {

        ACTIVE, INACTIVE, SOLVED, DELETED;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public Discussion(long id, long authorID, int categoryID, State state,
            Timestamp createTime, Timestamp updateTime, String subject, int participants, int posts) {
        this.id = id;
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.subject = subject;
        this.participants = participants;
        this.posts = posts;
    }

    public Discussion(Builder builder) {
        this.id = builder.id;
        this.authorID = builder.authorID;
        this.categoryID = builder.categoryID;
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
        return this.authorID;
    }

    public int getCategoryId() {
        return this.categoryID;
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
    
    public DiscussionContent getContent() {
        return discussionContentDao.findByDiscussionId(id);
    }

    public Object[] toObject() {
        return new Object[]{
            (id == Builder.ILLEGAL_ID ? null : id), (authorID == Builder.ILLEGAL_ID ? null : authorID),
            categoryID, state.ordinal(), createTime, updateTime,
            subject, participants, posts
        };
    }
//    
//    public Object[] toObjectWithoutID() {
//        return new Object[] {
//            authorID, categoryID, state.ordinal(), createTime, updateTime,
//            subject, participants, posts
//        };
//    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(Column.ID.toString(), String.valueOf(id));
        map.put(Column.AUTHOR_ID.toString(), String.valueOf(authorID));
        map.put(Column.CATEGORY_ID.toString(), String.valueOf(categoryID));
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

        public static final long ILLEGAL_ID = -1;
        public static final int ILLEGAL_CATEGORY = -1;
        public static final int ILLEGAL_PARTICIPANTS = -1;
        public static final int ILLEGAL_POSTS = -1;

        public static final int DEFAULT_PARTICIPANTS = 1;
        public static final int DEFAULT_POSTS = 0;

        private long id = ILLEGAL_ID;
        private long authorID = ILLEGAL_ID;
        private int categoryID = ILLEGAL_CATEGORY;
        private State state;
        private Timestamp createTime;
        private Timestamp updateTime;
        private String subject;
        private int participants = ILLEGAL_PARTICIPANTS;
        private int posts = ILLEGAL_POSTS;

        public Builder() {

        }

        public void id(long id) {
            if (id == ILLEGAL_ID) {
                throw new NullPointerException("id is illegal.");
            }
            this.id = id;
        }

        public void authorID(long authorID) {
            if (authorID == ILLEGAL_ID) {
                throw new NullPointerException("author id is illegal.");
            }
            this.authorID = authorID;
        }

        public void categoryID(int categoryID) {
            // here should be min_category_id <= category_id && category_id <= max_category_id
            if (categoryID < 0) {
                throw new NullPointerException("wrong category id : " + categoryID);
            }
            this.categoryID = categoryID;
        }

        public void state(int state) {
            for (State s : State.values()) {
                if (state == s.ordinal()) {
                    this.state = s;
                    return;
                }
            }
            throw new IllegalArgumentException("illegal state: " + state);
        }

        public void state(State state) {
            this.state = state;
        }

        public void createTime(Timestamp createTime) {
            if (createTime == null) {
                throw new NullPointerException("create time is null.");
            }
            this.createTime = createTime;
        }

        public void updateTime(Timestamp updateTime) {
            if (updateTime == null) {
                throw new NullPointerException("update time is null.");
            }
            this.updateTime = updateTime;
        }

        public void subject(String subject) {
            if (subject == null) {
                throw new NullPointerException("subject is null.");
            }
            this.subject = subject;
        }

        public void participants(int participants) {
            if (participants < ILLEGAL_PARTICIPANTS) {
                throw new NullPointerException("wrong participants : " + participants);
            }
            this.participants = participants;
        }

        public void posts(int posts) {
            if (posts < ILLEGAL_POSTS) {
                throw new NullPointerException("wrong posts : " + posts);
            }
            this.posts = posts;
        }

        public Discussion build() {
            if (id == ILLEGAL_ID) {
                throw new IllegalStateException("id is not specified.");
            }
            if (authorID == ILLEGAL_ID) {
                throw new IllegalStateException("author is not specified.");
            }
            if (subject == null) {
                throw new IllegalStateException("Subject is null.");
            }
            if (categoryID == ILLEGAL_CATEGORY) {
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
            if (participants == ILLEGAL_PARTICIPANTS) {
                throw new IllegalStateException("participants is not specified.");
            }
            if (posts == ILLEGAL_POSTS) {
                throw new IllegalStateException("posts is not specified.");
            }
            return new Discussion(this);
        }

        public Discussion buildForInsert() {
            if (id != ILLEGAL_ID) {
                throw new IllegalStateException("id is specified.");
            }
            if (authorID == ILLEGAL_ID) {
                throw new IllegalStateException("author is not specified.");
            }
            if (subject == null) {
                throw new IllegalStateException("Subject is null.");
            }
            if (categoryID == ILLEGAL_CATEGORY) {
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

    public static class list {

        public static List<Map<String, String>> toMapList(List<Discussion> dls) {
            List<Map<String, String>> mls = new ArrayList<>();
            for (Discussion dis : dls) {
                mls.add(dis.toMap());
            }
            return mls;
        }
    }
}
