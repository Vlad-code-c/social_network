package com.example.social_network.controller;

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
        model.addAttribute("name", "Vladz");
        model.addAttribute("id", "@Vladz5464");
        model.addAttribute("joined", "Joined September 18, 2020");
        model.addAttribute("avatar", "https://yt3.ggpht.com/-Ev00jf2SDp0/AAAAAAAAAAI/AAAAAAAAAAA/K28gnaebNfU/s900-c-k-no-rj-c0xffffff/photo.jpg");

        return "profile";
    }








}
