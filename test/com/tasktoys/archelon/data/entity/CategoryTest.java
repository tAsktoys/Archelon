/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import com.tasktoys.archelon.data.entity.Category.Builder;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author YuichiroSato
 * @since 0.2
 */
public class CategoryTest {

    @Test
    public void testBuild() {
        Category category = new Builder().build(1, "math");
        assertEquals(1, category.getId());
        assertEquals("math", category.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalCategoryId() {
        new Builder().build(null, "math");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalName() {
        new Builder().build(1, null);
    }
}
