package com.example.social_network.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;

//    private Long senderId;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private Date sended_at;

//    @OneToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "chat_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private MyUser user;                                    //sender_id

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSended_at() {
        return sended_at;
    }

    public void setSended_at(Date sended_at) {
        this.sended_at = sended_at;
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
