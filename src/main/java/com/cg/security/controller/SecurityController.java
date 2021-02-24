package com.cg.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/user")
    public String userPage(){
        return "user";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }

}
