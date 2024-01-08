package com.tuto.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView("admin/home");

        // You can add model attributes if needed
        modelAndView.addObject("pageTitle", "Admin Home Page");

        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView adminUsers() {
        ModelAndView modelAndView = new ModelAndView("admin/users");

        modelAndView.addObject("pageTitle", "Users page");

        return modelAndView;
    }
}
