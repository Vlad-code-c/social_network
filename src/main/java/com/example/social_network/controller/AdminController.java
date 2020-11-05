package com.example.social_network.controller;

import com.example.social_network.entity.MyUser;
import com.example.social_network.entity.Role;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/admin")
    public String admin(Model model){
        MyUser user = getUser();
        model.addAttribute("user", user);

        if (user.isAdmin()) {
            model.addAttribute("users", userRepository.findAll());
            return "admin_page";
        }

        return "main";

    }

    //TODO: SQL Migration
    @GetMapping("/addAdmin")
    public String addAdmin(Model model){
        //ong id, String username, String password, Set<Role> roles, String bio,
        // String profile_photo_url, Date birthday, String email, boolean is_active,
        // boolean is_blocked, Date created_at, Date updated_at

        MyUser user = new MyUser();
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setUsername("Admin");
        user.setPassword("123");

        customUserDetailsService.saveUser(user);

        model.addAttribute("user", user);

        return "main";
    }

    public MyUser getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        MyUser user = userRepository.findByUsername(currentPrincipalName);

        return user;
    }
}
