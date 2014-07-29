/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * The global exception resolver.
 *
 * @author mikan
 * @since 0.1
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = Logger.getLogger(ExceptionResolver.class.getName());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object o, Exception e) {
        log.log(Level.WARNING, "ERROR", e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ErrorController.ATTR_MESSAGE, "error.internal");
        modelAndView.addObject(ErrorController.ATTR_EXCEPTION, 
                "<strong>" + e.getClass().getSimpleName() + ": </strong>" + e.getMessage() + "<br/>"
                + "<br/><div id=\"stacktrace\">" + getStackTraceString(e) + "</div>"
        );
        modelAndView.setViewName(ErrorController.VIEW);
        return modelAndView;
    }

    private String getStackTraceString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        String text = sw.toString();
        text = text.replaceAll(System.getProperty("line.separator"), "<br/>");
        text = text.replaceAll("\t", "&nbsp; &nbsp; ");
        return text;
    }
}
