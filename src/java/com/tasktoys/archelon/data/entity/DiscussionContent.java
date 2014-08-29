/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.util.ArrayList;
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

    private String description = null;
    private String math = null;
    private String fig = null;
    private String svg = null;
    
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
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setMath(String math) {
        this.math = math;
    }
    
    public void setFig(String fig) {
        this.fig = fig;
    }
    
    public void setSvg(String svg) {
        this.svg = svg;
    }
    
    public void addPost(long AuthorId) {
        if (posts == null)
            posts = new ArrayList<>();
        Post post = new Post();
        post.setAuthorId(AuthorId);
        post.setDescription(description);
        post.setMath(math);
        post.setFig(fig);
        post.setSvg(svg);
        posts.add(post);
        description = null;
        math = null;
        fig = null;
        svg = null;
    }
    
    public long getFirstAuthorId() {
        if (posts == null || posts.isEmpty())
            return -1;
        return posts.get(0).getAuthorId();
    }
}

class Post {
    
    private long authorId;
    private String description;
    private String math;
    private String fig;
    private String svg;

    public long getAuthorId() {
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
    
}
