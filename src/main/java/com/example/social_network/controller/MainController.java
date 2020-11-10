package com.example.social_network.controller;

import com.example.social_network.entity.MyUser;
import com.example.social_network.entity.Role;
import com.example.social_network.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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



    @GetMapping("/profile/{user_id}")
    public String profile(Model model, @PathVariable(name = "user_id") Long user_id){
        MyUser user;

        if (user_id == null){
            user = getUser();
        } else {
            user = userRepository.findFirstById(user_id);
            if (user == null){
                return "error/404";
            }
        }


        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("user", getUser());
        return "profile";
    }

    @PostMapping("/changeFriendStatus")
    @ResponseBody
    public String changeFriendStatus(
            @RequestParam(name = "userid", required = true) final Long userId){
        MyUser curentUser = getUser();
        MyUser newUser = userRepository.findFirstById(userId);

        Logger logger = LoggerFactory.getLogger(MainController.class);
        logger.error("Changed Friend Status");

        return "Succes";
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
