package com.xjst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SwaggerController {
    /**
     * @Description 进入Swagger-ui.html页面
     * @Author Ethan
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     * @Version 1.0
     */
    @RequestMapping("/swagger-ui")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("swagger/index");
        return mv;
    }
}
