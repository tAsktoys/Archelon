/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of <code>ranking.jsp</code>.
 * 
 * @author YuichiroSato
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/ranking")
public class RankingController {

    @RequestMapping(method = RequestMethod.GET)
    public String getRanking(Model model) {
        return "ranking";
    }
}
