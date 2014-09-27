/*
 * Copyright(C) 2014 tAsktoys. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

import java.io.Serializable;

/**
 * The category entity.
 *
 * @author YuichiroSato
 * @author mikan
 * @since 0.1
 * @version 0.2
 * @serial
 */
public final class Category implements Serializable {

    /**
     * Serial version 1.
     *
     * @since 0.2
     */
    private static final long serialVersionUID = 1L;

    public static final int ILLEGAL_ID = -1;
    
    private final int id;
    private final String name;

    private Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public static class Builder {

        public Builder() {

        }

        public Category build(Integer id, String name) {
            if (id == null) {
                throw new IllegalArgumentException("category id is null.");
            } else if (name == null) {
                throw new IllegalArgumentException("category name is null.");
            } else {
                return new Category(id, name);
            }
        }
    }
}
