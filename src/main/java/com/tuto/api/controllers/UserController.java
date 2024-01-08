package com.tuto.api.controllers;

import com.tuto.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public String createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.createUser(username, password, role);

        return "redirect:/admin/users";
    }
}
