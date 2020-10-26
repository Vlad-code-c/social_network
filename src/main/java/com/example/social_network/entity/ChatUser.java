package com.example.social_network.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chat_user")
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

    public ChatUser() {}

    public String getAvatarImage(){
        return chat.getAvatarImage();
    }

    public String getChat_name(){
        return chat.getChat_name();
    }

    public String getLastMessage(){
        return chat.getLastMessage();
    }

    public Date getLastMessageDate(){
        return chat.getLastMessageDate();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}
