package com.example.social_network.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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


    static public boolean ifChatExist(List<Chat> chats, MyUser user1, MyUser user2){
        //1. Create chat
        //2. If in chats exists chat which contains exactly (and only) that 2 users, return false
        //3. Else return true

        //TODO: Improve this shit

        for (Chat chat : chats) {
            Set<ChatUser> users = chat.getUsers();
            if (users.size() == 2){
                if ((((ChatUser) users.toArray()[0]).getUser().getId() == user1.getId() ||
                        ((ChatUser) users.toArray()[1]).getUser().getId() == user1.getId()) &&
                        (((ChatUser) users.toArray()[0]).getUser().getId() == user2.getId() ||
                                ((ChatUser) users.toArray()[1]).getUser().getId() == user2.getId())){
                    return false;

                }
            }
        }
        return true;
    }


    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
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
