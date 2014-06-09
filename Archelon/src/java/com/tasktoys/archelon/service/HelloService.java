/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

/**
 * Interface of Hello Service.
 *
 * @author mikan
 * @since 0.1
 */
public interface HelloService {
    
    /**
     * Say hello.
     * 
     * @param name name
     * @return hello string
     * @throws NullPointerException If name is null.
     */
    public String sayHello(String name);
}
