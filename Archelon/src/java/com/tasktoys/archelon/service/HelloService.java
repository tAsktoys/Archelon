/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service;

/**
 * The Hello Service.
 *
 * @see https://netbeans.org/kb/docs/web/quickstart-webapps-spring_ja.html
 * @author mikan
 */
public class HelloService {

    /**
     * Say hello.
     * 
     * @param name name
     * @return hello string
     * @throws NullPointerException If name is null.
     */
    public String sayHello(String name) {
        if (name == null)
            throw new NullPointerException("name is null");
        return "Hello " + name + "!";
    }
}
