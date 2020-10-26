package com.example.social_network.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {

//    @Autowired
//    private MessageRepository messageRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_id")
    private Long chatId;

    private String chat_name;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private Date created_at;


    @OneToMany(mappedBy = "chat", cascade = {CascadeType.ALL})
    private Set<ChatUser> users = new HashSet<ChatUser>();

    @OneToMany(mappedBy = "chat", cascade = {CascadeType.ALL})
    private Set<Message> messages = new HashSet<Message>();

    public Chat(){}


    public String getAvatarImage(){
        MyUser user = new MyUser();
        user.setProfile_photo_url("https://www.travelcontinuously.com/wp-content/uploads/2018/04/empty-avatar.png");

        if (users.size() != 0)
            user = ((ChatUser) users.toArray()[0]).getUser();

        return user.getProfile_photo_url();
    }

    public String getLastMessage(){

        if (this.messages.size() != 0){
            return ((Message) this.messages.toArray()[messages.size() - 1]).getContent();
        } else {
            return "None";
        }
    }

    public Date getLastMessageDate(){

        if (this.messages.size() != 0){
            return ((Message) this.messages.toArray()[messages.size() - 1]).getSended_at();
        } else {
            return null;
        }
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
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

    public Set<ChatUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ChatUser> users) {
        this.users = users;
    }

//    public Message getMessage() {
//        return messages;
//    }
//
//    public void setMessage(Message messages) {
//        this.messages = messages;
//    }
}
