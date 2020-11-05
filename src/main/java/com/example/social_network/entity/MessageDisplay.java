package com.example.social_network.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class MessageDisplay {

    private String content;
    private Date sended_at;

    private boolean is_right;

    private MyUser user;


    public static ArrayList<MessageDisplay> generateMessages(Set<Message> messages, MyUser user){
        ArrayList<MessageDisplay> messagesDisplay = new ArrayList<MessageDisplay>();

        for (Message message : messages) {
            MessageDisplay messageDisplay = new MessageDisplay();
            messageDisplay.setContent(message.getContent());
            messageDisplay.setSended_at(message.getSended_at());
            messageDisplay.setUser(message.getUser());

            messageDisplay.setIs_right(message.getUser().getId() == user.getId());
        }

        return messagesDisplay;
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

    public boolean isIs_right() {
        return is_right;
    }

    public void setIs_right(boolean is_right) {
        this.is_right = is_right;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }


}
