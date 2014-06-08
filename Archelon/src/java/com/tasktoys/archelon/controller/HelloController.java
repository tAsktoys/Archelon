/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import com.tasktoys.archelon.service.HelloService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Hello Controller.
 *
 * @author mikan
 */
@Controller
@RequestMapping(value="/hello")
public class HelloController {

    private HelloService helloService;
    
    public HelloController() {
        
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("helloMessage", helloService.sayHello("ほげほげ"));
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("helloMessage", helloService.sayHello("ほげほげ"));
        return "hello";
    } 
}
