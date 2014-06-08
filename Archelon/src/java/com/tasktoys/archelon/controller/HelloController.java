/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Hello Controller.
 *
 * @author mikan
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private HelloServiceImpl helloService;

    public HelloController() {
    }

    public void setHelloService(HelloServiceImpl helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHello(Model model) {
        model.addAttribute("helloMessage", helloService.sayHello("ほげほげ"));
        return "hello";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public String getName(@PathVariable String name, Model model) {
        model.addAttribute("helloMessage", "name is " + name);
        return "hello";
    }
}
