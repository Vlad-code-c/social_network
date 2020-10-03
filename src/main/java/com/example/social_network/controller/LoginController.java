package com.example.social_network.controller;

import com.example.social_network.entity.MyUser;
import com.example.social_network.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class LoginController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    static MyUser myUser;

    @GetMapping("/signup")
    public String signUp(WebRequest request, Model model){
        model.addAttribute("userForm", myUser);

        return "/signup";
    }

    @GetMapping("/login")
    public String logIn(Model model){
        model.addAttribute("userForm", myUser);

        return "/login";
    }


    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userForm") MyUser user, Model model){

        if (!customUserDetailsService.saveUser(user)){
            model.addAttribute("usernameError", "User exists!");
            return "/signup";
        }

        return "/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("userForm") MyUser user){

        myUser = user;
        if (user == null){
            System.out.println("Null");
        } else {
            System.out.println("NotNull");
        }

        return "/main";
    }

    @GetMapping("*")
    public String v(){

        return "main_page_auth";
    }

}
