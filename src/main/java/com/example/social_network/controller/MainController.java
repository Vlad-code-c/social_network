package com.example.social_network.controller;

import com.example.social_network.entity.MyUser;
import com.example.social_network.entity.Role;
import com.example.social_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/main")
    public String main(Model model){
        MyUser user = getUser();
        model.addAttribute("user", user);

        return "main";
    }

    @GetMapping("/")
    public String mainM(Model model){
        MyUser user = getUser();
        model.addAttribute("user", user);

        return "main";
    }



    @GetMapping("/profile")
    public String profile(Model model){
        MyUser user = getUser();

        model.addAttribute("user", user);


        return "profile";
    }



    public MyUser getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        MyUser user = userRepository.findByUsername(currentPrincipalName);

        if (user == null){
            user = new MyUser();

            user.setUsername("Username");
            user.setRoles(Collections.singleton(Role.USER));
            user.setProfile_photo_url("https://cdn.iconscout.com/icon/free/png-256/avatar-370-456322.png");
            user.setCreated_at(Calendar.getInstance().getTime());
            user.setId(0l);
        }

        return user;
    }


}
