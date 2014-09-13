package com.tasktoys.archelon.data.dao.jdbc;

import com.tasktoys.archelon.data.dao.ActivityDao;
import com.tasktoys.archelon.data.entity.Activity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
@Repository
public class JdbcActivityDao implements ActivityDao {
    
    @Override
    public List<Activity> findLatestActivities(int n) {
        return new ArrayList<>();
    }
}
