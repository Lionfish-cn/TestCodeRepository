package com.code.repository.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SkipController {
    @RequestMapping("/skip")
    public ModelAndView skip(HttpServletRequest request) {
        String path = request.getParameter("path");
        ModelAndView mov = new ModelAndView();
        mov.setViewName(path);
        return mov;
    }
}
