package com.example.social_network.controller;

import com.example.social_network.entity.*;
import com.example.social_network.repository.ChatRepository;
import com.example.social_network.repository.MessageRepository;
import com.example.social_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.logging.Logger;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages")
    public String messages(Model model){
        MyUser user = getUser();

        model.addAttribute("user", user);
        model.addAttribute("chats", user.getChats());

        //TODO:
        //Change findAll to 'findAllFriends'
        model.addAttribute("friends", userRepository.findAll());

        return "messages";

    }


    @RequestMapping("/messages/create-chat/{invited_id}")
    public String createChat(
            Model model,
            @PathVariable("invited_id") final Long user_id
    ){


        Chat chat = new Chat();
        chat.setCreated_at(Calendar.getInstance().getTime());

        MyUser user = getUser();
        MyUser invited = userRepository.findById(user_id).get();

        ChatUser chatUser1 = new ChatUser();
        chatUser1.setChat(chat);
        chatUser1.setUser(user);

        ChatUser chatUser2 = new ChatUser();
        chatUser2.setChat(chat);
        chatUser2.setUser(invited);

        user.getChats().add(chatUser1);
        invited.getChats().add(chatUser1);
        user.getChats().add(chatUser2);
        invited.getChats().add(chatUser2);

        chat.getUsers().add(chatUser1);
        chat.getUsers().add(chatUser2);


        chat.setChat_name(chatUser1.getUser().getUsername() + ", " +
                            chatUser2.getUser().getUsername());

        chatRepository.save(chat);


        model.addAttribute("user", user);
        model.addAttribute("chat_id", chat.getChatId());
        model.addAttribute("chats", user.getChats());

        return "messages";
    }
    
    
    

//    @PostMapping("/messages/{chat_id}")
//    public String sendMessage(
//        Model model,
//        @PathVariable("chat_id") final Long chat_id,
//        @RequestParam(value = "sender_id", required = true) Long sender_id,
//        @RequestParam(value = "message", required = true) String message
//    ){
//
//        Chat chat = chatRepository.findByChatId(sender_id);
//        if (chat == null)
//            //TODO:
//            //Add error page
//            return "error";
//
//        Message mess = new Message();
//        mess.setSenderId(sender_id);
//        mess.setChat(chat);
//        mess.setContent(message);
//        mess.setSended_at(Calendar.getInstance().getTime());
//
//        model.addAttribute("user", getUser());
//
//        //TODO:
//        //Change finAll to findAllById
//        model.addAttribute("messages", messageRepository.findAll());
//
//        return "messages/" + chat_id;
//    }

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
