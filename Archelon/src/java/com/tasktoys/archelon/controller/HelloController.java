/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.service.HelloService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * The Hello Controller.
 *
 * @see https://netbeans.org/kb/docs/web/quickstart-webapps-spring_ja.html
 * @author mikan
 */
public class HelloController extends AbstractController {

    private HelloService helloService;
    
    public HelloController() {
        
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("helloMessage", helloService.sayHello("ほげほげ"));
        return mv;
    }
}
