package com.xjst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MvcControlelr {
    @RequestMapping("/aa")
    public ModelAndView aa(){
        ModelAndView model = new ModelAndView("/static/file.jsp");
        return model;
    }
    @RequestMapping("/bb")
    public String bb(){
        return "/static/file.jsp";
    }
}
