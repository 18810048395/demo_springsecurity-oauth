package com.example.springsecurityoauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义 LoginController
 */
@Controller
public class LoginController {
    @RequestMapping("/toLogin")
    public String login() {
        return "loginPage";
    }
}