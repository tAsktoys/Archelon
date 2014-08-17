/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

import com.tasktoys.archelon.data.entity.Discussion;
import java.util.List;
import java.util.Map;

/**
 *
 * @author YuichiroSato
 */
public interface DiscussionService {
    
    /** Discussion Dao wrapper
     * 
     * @param n
     * @return 
     */
    public List<Discussion> getNewestDiscussionList(int n);
    public List<Discussion> getDiscussionListAfter(long id, int n);
    public List<Discussion> getDiscussionListBefore(long id, int n);
    public List<Discussion> getNewestDiscussionListByMainCategory(int n, int main_id);
    public List<Discussion> getDiscussionListWithMainCategoryBefore(long id, int n, int main_id);
    public List<Discussion> getNewestDiscussionListBySubCategory(int n, int main_id, int sub_id);
    public List<Discussion> getDiscussionListWithSubCategoryBefore(long id, int n, int main_id, int sub_id);
    public void insertDiscussion(Discussion discussion);
    
    /** Replace <code>long</code> author id to <code>String</code> author name in discussions
     * 
     * @param dls list of discussions
     * @return list of discussions converted to maps with replacing author id to author name 
     */
    public List<Map<String, String>> replaceAuthorIDToAuthorName(List<Discussion> dls);
}
