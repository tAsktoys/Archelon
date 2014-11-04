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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Yuichiro
 * @since 0.2
 */
public class DiscussionServiceImplTest {

    DiscussionServiceImpl discussionServiceImpl = new DiscussionServiceImpl();
    Class discussionServiceImplClass = DiscussionServiceImpl.class;

    @Test
    public void testcalculateEndPageNumber() {
        Class[] argsType = {int.class, int.class};
        try {
            Method method = discussionServiceImplClass.getDeclaredMethod("calculateEndPageNumber", argsType);
            method.setAccessible(true);
            int result1 = (int) method.invoke(discussionServiceImpl, 95, 10);
            assertEquals(10, result1);
            int result2 = (int) method.invoke(discussionServiceImpl, 70, 10);
            assertEquals(7, result2);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            fail();
        }
    }

    @Test
    public void testcreateDiscussionLinkByEndPage() {
        Class[] argsType = {String.class, int.class, int.class};
        try {
            Method method = discussionServiceImplClass.getDeclaredMethod("createDiscussionLinkByEndPage", argsType);
            method.setAccessible(true);
            Map<String, Map<String, String>> result
                    = (Map<String, Map<String, String>>) method.invoke(discussionServiceImpl, "test", 3, 5);
            Map<String, String> subModel = result.get("test");
            assertEquals(5, subModel.size());
            assertEquals(2, subModel.get("previous"));
            assertEquals(3, subModel.get("current"));
            assertEquals(4, subModel.get("next"));
            assertEquals(5, subModel.get("end"));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            fail();
        }
    }

    @Test
    public void testcreateDiscussionLinkByEndPage2() {
        Class[] argsType = {String.class, int.class, int.class};
        try {
            Method method = discussionServiceImplClass.getDeclaredMethod("createDiscussionLinkByEndPage", argsType);
            method.setAccessible(true);
            Map<String, Map<String, String>> result
                    = (Map<String, Map<String, String>>) method.invoke(discussionServiceImpl, "test", 1, 5);
            Map<String, String> subModel = result.get("test");
            assertEquals(5, subModel.size());
            assertEquals(1, subModel.get("previous"));
            assertEquals(1, subModel.get("current"));
            assertEquals(2, subModel.get("next"));
            assertEquals(5, subModel.get("end"));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            fail();
        }
    }

    @Test
    public void testcreateDiscussionLinkByEndPage3() {
        Class[] argsType = {String.class, int.class, int.class};
        try {
            Method method = discussionServiceImplClass.getDeclaredMethod("createDiscussionLinkByEndPage", argsType);
            method.setAccessible(true);
            Map<String, Map<String, String>> result
                    = (Map<String, Map<String, String>>) method.invoke(discussionServiceImpl, "test", 5, 5);
            Map<String, String> subModel = result.get("test");
            assertEquals(5, subModel.size());
            assertEquals(4, subModel.get("previous"));
            assertEquals(5, subModel.get("current"));
            assertEquals(5, subModel.get("next"));
            assertEquals(5, subModel.get("end"));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            fail();
        }
    }

    @Test
    public void testcreatePageNumbers() {
        try {
            Method method = discussionServiceImplClass.getDeclaredMethod("createPageNumbers", int.class);
            method.setAccessible(true);
            Map<String, Object> result = (Map<String, Object>) method.invoke(discussionServiceImpl, 4);
            List<Integer> list = (List<Integer>) result.get("pageNumberList");
            assertEquals(4, list.size());
            assertEquals((Integer) 1, list.get(0));
            assertEquals((Integer) 2, list.get(1));
            assertEquals((Integer) 3, list.get(2));
            assertEquals((Integer) 4, list.get(3));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            fail();
        }
    }
}
