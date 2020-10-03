package com.example.social_network.controller;

import com.example.social_network.entity.MyUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/main")
    public String main(Model model){
        return "main";
    }

    @GetMapping("/")
    public String mainM(Model model){
        return "main";
    }



    @GetMapping("/profile")
    public String profile(Model model){
        MyUser user = LoginController.myUser;

        if (user == null){
            user = new MyUser();

            user.setUsername("Username");
            user.setProfile_photo_url("https://cdn.iconscout.com/icon/free/png-256/avatar-370-456322.png");
            user.setId(423l);
        }


        model.addAttribute("name", user.getUsername());
        model.addAttribute("id", "@" + user.getUsername().trim() + user.getId());
        model.addAttribute("joined", "Joined September 18, 2020");
        model.addAttribute("avatar", user.getProfile_photo_url());

        return "profile";
    }








}
