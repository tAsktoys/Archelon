/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */

package com.tasktoys.archelon.data.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yuichiro
 */
public class Discussion {
    
    private final Long id;
    private final Long author_id;
    private final int category_id;
    private final int state;
    private final Timestamp create_time;
    private final Timestamp update_time;
    private final String subject;
    private final int participants;
    private final int posts;
    
    public enum Column {
        id, author_id, category_id, state, create_time, update_time, subject,
        participants, posts
    }
    
    public Discussion(Long id, Long author_id, int category_id, int state,
            Timestamp create_time, Timestamp update_time, String subject, int participants, int posts) {
        this.id = id;
        this.author_id = author_id;
        this.category_id = category_id;
        this.state = state;
        this.create_time = create_time;
        this.update_time = update_time;
        this.subject = subject;
        this.participants = participants;
        this.posts = posts;
    }
    
    public Discussion(Builder builder) {
        this.id = builder.id;
        this.author_id = builder.author_id;
        this.category_id = builder.category_id;
        this.state = builder.state;
        this.create_time = builder.create_time;
        this.update_time = builder.update_time;
        this.subject = builder.subject;
        this.participants = builder.participants;
        this.posts = builder.posts;
    }
    
    public Long getID() {
        return this.id;
    }
    
    public Long getAuthorID() {
        return this.author_id;
    }
    
    public int category_id() {
        return this.category_id;
    }
    
    public int state() {
        return this.state;
    }
    
    public Timestamp create_time() {
        return this.create_time;
    }
    
    public Timestamp update_time() {
        return this.update_time;
    }
    
    public String subject() {
        return this.subject;
    }
    
    public int participants() {
        return this.participants;
    }
    
    public int posts() {
        return this.posts;
    }
    
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(Column.id.name(), id.toString());
        map.put(Column.author_id.name(), author_id.toString());
        map.put(Column.category_id.name(), String.valueOf(category_id));
        map.put(Column.state.name(), String.valueOf(state));
        map.put(Column.create_time.name(), create_time.toString());
        map.put(Column.update_time.name(), update_time.toString());
        map.put(Column.subject.name(), subject);
        map.put(Column.participants.name(), String.valueOf(participants));
        map.put(Column.posts.name(), String.valueOf(posts));
        return map;
    }
    
    public Map<String, String> replaceAuthorIDToAuthorName(String name) {
        Map<String, String> map = this.toMap();
        map.remove(Column.author_id.name());
        map.put(Column.author_id.name(), name);
        return map;
    }
    
    public static class Builder {
        
        private Long id;
        private Long author_id;
        private int category_id;
        private int state;
        private Timestamp create_time;
        private Timestamp update_time;
        private String subject;
        private int participants;
        private int posts;
    
        public Builder() {
            
        }
        
        public void id(Long id) {
            if (id == null) {
                throw new NullPointerException("id is null.");
            }
            this.id = id;
        }
        
        public void author_id(Long author_id) {
            if (author_id == null) {
                throw new NullPointerException("author id is null.");
            }
            this.author_id = author_id;
        }
        
        public void category_id(int category_id) {
            // here should be min_category_id <= category_id && category_id <= max_category_id
            if (category_id < 0) {
                throw new NullPointerException("wrong category id : " + category_id);
            }
            this.category_id = category_id;
        }
        
        public void state(int state) {
            // here should be min_state <= state && state <= max_state
            if (state < 0) {
                throw new NullPointerException("ilegal state : " + state);
            }
            this.state = state;
        }
        
        public void create_time(Timestamp create_time) {
            if (create_time == null) {
                throw new NullPointerException("create time is null.");
            }
            this.create_time = create_time;
        }
        
        public void update_time(Timestamp update_time) {
            if (update_time == null) {
                throw new NullPointerException("update time is null.");
            }
            this.update_time = update_time;
        }
        
        public void subject(String subject) {
            if (subject == null) {
                throw new NullPointerException("subject is null.");
            }
            this.subject = subject;
        }
        
        public void participants(int participants) {
            if (participants < 1) {
                throw new NullPointerException("wrong participants : " + participants);
            }
            this.participants = participants;
        }
        
        public void posts(int posts) {
            if (posts < 0) {
                throw new NullPointerException("wrong posts : " + posts);
            }
            this.posts = posts;
        }
        
        public Discussion build() {
            return new Discussion(this);
        }
    }
    
    public static class list {
        
        public static List<Map<String, String>> toMapList(List<Discussion> dls) {
            List<Map<String, String>> mls = new ArrayList<>();
            for(Discussion dis : dls) {
                mls.add(dis.toMap());
            }
            return mls;
        }
    }
}
