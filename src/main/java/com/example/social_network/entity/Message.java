package com.example.social_network.entity;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;

    private Long senderId;
    private String content;


    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
