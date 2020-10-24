package com.example.social_network.controller;

import com.example.social_network.entity.Message;
import com.example.social_network.entity.MyUser;
import com.example.social_network.entity.Role;
import com.example.social_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.Collections;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("topic/public")
    public Message sendMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor){
        //Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSenderId());
        return message;
    }


    @GetMapping("/messages")
    public String messages(Model model){
        MyUser user = getUser();

        model.addAttribute("user", user);


        return "messages";
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
