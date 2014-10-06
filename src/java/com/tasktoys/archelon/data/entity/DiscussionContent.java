/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 *
 * @author mikan
 */
@Document
public class DiscussionContent {

    @Id
    private String id;

    @Field
    private long discussionId;

    @Field
    private String subject;

    @Field
    private List<Post> posts;

    @Field
    private List<Long> participants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(long discussionId) {
        this.discussionId = discussionId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void addPost(Post post) {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(post);
    }

    public void addPost(long authorId) {
        if (authorId <= User.ILLEGAL_ID) {
            return;
        }
        Post post = new Post();
        post.setAuthorId(authorId);
        addPost(post);
    }

    
    public void addPost(long authorId, String discription) {
        if (authorId <= User.ILLEGAL_ID || discription == null) {
            return;
        }
        Post post = new Post();
        post.setAuthorId(authorId);
        post.setDescription(discription);
        addPost(post);
    }
    
    public List<Post> getPosts() {
        return this.posts;
    }

    public void addParticipants(long userId) {
        if (participants == null) {
            participants = new ArrayList<>();
        }
        participants.add(userId);
    }

    public List<Long> getParticipateMember() {
        return participants;
    }

    public boolean isParticipate(long userId) {
        return participants.contains(userId);
    }

    public int getParticipants() {
        return participants.size();
    }

    public long getFirstAuthorId() {
        if (posts == null || posts.isEmpty()) {
            return -1;
        }
        return posts.get(0).getAuthorId();
    }

    public static class Post {

        private Long authorId;
        private Date postTimeStamp;
        private String description;
        private String math;
        private String fig;
        private String svg;

        public Post() {
            Calendar cal = Calendar.getInstance();
            this.postTimeStamp = cal.getTime();
        }

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(long authorId) {
            this.authorId = authorId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMath() {
            return math;
        }

        public void setMath(String math) {
            this.math = math;
        }

        public String getFig() {
            return fig;
        }

        public void setFig(String fig) {
            this.fig = fig;
        }

        public String getSvg() {
            return svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }

        public Date getPostTimeStamp() {
            return postTimeStamp;
        }

        public boolean equals(Post post) {
            if (post == null) {
                return false;
            }
            return (authorId == null ? post.getAuthorId() == null : authorId.equals(post.getAuthorId()))
                    && (description == null ? post.getDescription() == null : description.equals(post.getDescription()))
                    && (math == null ? post.getMath() == null : math.equals(post.getMath()))
                    && (fig == null ? post.getFig() == null : fig.equals(post.getFig()))
                    && (svg == null ? post.getSvg() == null : svg.equals(post.getSvg()));
        }
    }
}
