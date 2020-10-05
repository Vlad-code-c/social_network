package com.example.social_network.controller;

import com.example.social_network.entity.MyUser;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @GetMapping("/signup")
    public String signUp(WebRequest request, Model model){
        model.addAttribute("userForm", new MyUser());

        return "/signup";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOWWWWWWWWWWWWWWWWW GEEEEEEET");

        return new ModelAndView("login","login", new MyUser());
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userForm") MyUser user, Model model){

        if (!customUserDetailsService.saveUser(user)){
            model.addAttribute("usernameError", "User exists!");
            return "/signup";
        }

        return "/login";
    }

    @GetMapping("*")
    public String v(){
        return "main_page_auth";
    }

}
