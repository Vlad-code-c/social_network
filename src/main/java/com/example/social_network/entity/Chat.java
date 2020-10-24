package com.example.social_network.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;

    private String chat_name;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private Date created_at;


    @ManyToMany(mappedBy = "chats", cascade = {CascadeType.ALL})
    private Set<MyUser> users = new HashSet<MyUser>();

    @OneToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                mappedBy = "user")
    private Message message;

    public Chat(){}

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Set<MyUser> getUsers() {
        return users;
    }

    public void setUsers(Set<MyUser> users) {
        this.users = users;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
