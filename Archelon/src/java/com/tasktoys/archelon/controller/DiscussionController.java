/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tasktoys.archelon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author 拓海
 */
public class DiscussionController extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("discussion");
        return mv;
    }
    
}
