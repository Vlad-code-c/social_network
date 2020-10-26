package com.example.social_network.controller;

import com.example.social_network.entity.MyUser;
import com.example.social_network.entity.Role;
import com.example.social_network.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

@Controller
public class LoginController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @GetMapping("/signup")
    public String signUp(WebRequest request, Model model){
        model.addAttribute("userForm", new MyUser());

        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userForm") MyUser user, Model model){
        user.setRoles(Collections.singleton(Role.USER));

        if (!customUserDetailsService.saveUser(user)){
            model.addAttribute("usernameError", "User exists!");
            return "signup";
        }
        return "login";
    }

    @GetMapping("*")
    public String v(){
        return "main_page_auth";
    }



}
