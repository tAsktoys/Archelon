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
    public List<Discussion> getDiscussionListAfter(Long id, int n);
    public List<Discussion> getDiscussionListBefor(Long id, int n);
    public List<Discussion> getNewestDiscussionListByMainCategory(int n, int main_id);
    public List<Discussion> getDiscussionListWithMainCategoryBefor(Long id, int n, int main_id);
    public List<Discussion> getNewestDiscussionListBySubCategory(int n, int main_id, int sub_id);
    public List<Discussion> getDiscussionListWithSubCategoryBefor(Long id, int n, int main_id, int sub_id);
    
    /**
     * 
     * @param dls
     * @return 
     */
    public List<Map<String, String>> replaceAuthorIDToAurthorName(List<Discussion> dls);
}
