/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.service.impl;

import com.tasktoys.archelon.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * The Hello Service.
 *
 * @author mikan
 * @since 0.1
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        if (name == null)
            throw new NullPointerException("name is null");
        return "Hello " + name + "!";
    }
}
