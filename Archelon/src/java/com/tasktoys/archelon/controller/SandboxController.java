package com.tasktoys.archelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller of discussion.jsp
 * 
 * @author bearing
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/sandbox")
public class SandboxController {
 @RequestMapping(method = RequestMethod.GET)
    public String getUserGuest(Model model) {
        model.addAttribute("id", "Guest");
        return "sandbox";//return the name of jsp file without  ".jsp"
    }
    
    @RequestMapping(value="/{id},{test}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") String _id , @PathVariable("test") String _test, Model model) {
        model.addAttribute("id", _id);
        model.addAttribute("test", _test);
        return "sandbox";
    }
}
