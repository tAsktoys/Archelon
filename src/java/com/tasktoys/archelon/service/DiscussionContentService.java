/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.DiscussionContent;

/**
 *
 * @author YuichiroSato
 * @version 0.1
 */
public interface DiscussionContentService {
    
    public DiscussionContent getDiscussionContent(long discussionId);
    public void updateDiscussionContent(DiscussionContent content);
}
