/*
 *   Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.data.entity.Category;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Yuichiro
 * @since 0.2
 */
public class CategoryServiceImplTest {

    @Test
    public void testtoMapList() {
        Class categoryServiceImpl = CategoryServiceImpl.class;
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(Category.Builder.build(2, "c1"));
        categoryList.add(Category.Builder.build(3, "c2"));

        List<Map<String, String>> expected = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("id", "2");
        map1.put("name", "c1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("id", "3");
        map2.put("name", "c2");
        expected.add(map1);
        expected.add(map2);

        try {
            Method toMapList = categoryServiceImpl.getDeclaredMethod("toMapList", List.class);
            toMapList.setAccessible(true);
            List<Map<String, String>> result
                    = (List<Map<String, String>>) toMapList.invoke(categoryServiceImpl, categoryList);
            assertEquals(expected, result);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
    }
}
