package com.example.social_network.controller;

import com.example.social_network.entity.*;
import com.example.social_network.repository.ChatRepository;
import com.example.social_network.repository.ChatUserRepository;
import com.example.social_network.repository.MessageRepository;
import com.example.social_network.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatUserRepository chatUserRepository;

    @GetMapping("/getMessages/{chat_id}")
    @ResponseBody
    public String test(@PathVariable(name = "chat_id", required = true) final Long chat_id){


        if (!isUserAllowedToChat(chat_id)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", "User dont have acces to this chat");
            return jsonObject.toString();
        }

        MyUser user = getUser();
        Chat curent_chat = chatRepository.findByChatId(chat_id);
        ArrayList<MessageDisplay> messageDisplays = MessageDisplay.generateMessages(curent_chat.getMessages(), user);


        JSONArray json_arr = new JSONArray();

        for (MessageDisplay messageDisplay : messageDisplays) {
            json_arr.put(messageDisplay.getHtmlMessage());
        }


        return json_arr.toString();
    }

    @PostMapping("/sendMessage/{chat_id}")
    @ResponseBody
    public String sendMessage(
            @PathVariable(value = "chat_id", required = true) final Long chat_id,
            @RequestParam(value = "message", required = true) String message
    ){

        if (!isUserAllowedToChat(chat_id)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", "User dont have acces to this chat");
            return jsonObject.toString();
        }

        MyUser user = getUser();

        Chat curent_chat = chatRepository.findByChatId(chat_id);
        if (curent_chat == null)
            return "error";

        Message new_mess = saveMessage(user, curent_chat, message);


        ArrayList<MessageDisplay> messageDisplays = MessageDisplay.generateMessages(new HashSet<>(Arrays.asList(new_mess)), user);

        JSONArray json_arr = new JSONArray();

        for (MessageDisplay messageDisplay : messageDisplays) {
            json_arr.put(messageDisplay.getHtmlMessage());
        }


        return json_arr.toString();

    }

    private void createChat(MyUser user1, MyUser user2){
        Chat chat = new Chat();
        chat.setCreated_at(Calendar.getInstance().getTime());

        ChatUser chatUser1 = new ChatUser();
        chatUser1.setChat(chat);
        chatUser1.setUser(user1);

        ChatUser chatUser2 = new ChatUser();
        chatUser2.setChat(chat);
        chatUser2.setUser(user2);

        user1.getChats().add(chatUser1);
        user1.getChats().add(chatUser2);
        user2.getChats().add(chatUser1);
        user2.getChats().add(chatUser2);

        chat.getUsers().add(chatUser1);
        chat.getUsers().add(chatUser2);

        chat.setChat_name(user1.getUsername() + ", " + user2.getUsername());

        chatRepository.save(chat);
    }

    private boolean isUserAllowedToChat(Long chatId){
        MyUser user = getUser();

        if (!chatUserRepository.findFirstByChat_ChatIdAndAndUser_Id(chatId, user.getId()).isEmpty()) {
            return true;
        }

        return false;
    }

    private MyUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        MyUser user = userRepository.findByUsername(currentPrincipalName);

        if (user == null) {
            user = new MyUser();

            user.setUsername("Username");
            user.setRoles(Collections.singleton(Role.USER));
            user.setProfile_photo_url("https://cdn.iconscout.com/icon/free/png-256/avatar-370-456322.png");
            user.setCreated_at(Calendar.getInstance().getTime());
            user.setId(0l);
        }

        return user;
    }

    private Message saveMessage(MyUser user, Chat curent_chat, String message){
        Message mess = new Message();
        mess.setUser(user);
        mess.setChat(curent_chat);
        mess.setContent(message);
        mess.setSended_at(Calendar.getInstance().getTime());

        curent_chat.getMessages().add(mess);
        messageRepository.save(mess);

        return mess;
    }
}
