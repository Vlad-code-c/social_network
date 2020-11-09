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

            messagesDisplay.add(messageDisplay);
        }

        return messagesDisplay;
    }

    public String getBackgroundUrl(){
        return "background-image: url(" + user.getProfile_photo_url() + ")";
    }

    public String getHtmlMessage(){
        return String.format("<div class=\"msg %s\">\n" +
                "                                    <div class=\"msg-img\" style=\"%s\"></div>\n" +
                "\n" +
                "                                    <div class=\"msg-bubble\">\n" +
                "                                        <div class=\"msg-info\">\n" +
                "                                            <div class=\"msg-info-name\" >%s</div>\n" +
                "                                            <div class=\"msg-info-time\" >%s</div>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <div class=\"msg-text\" >%s</div>\n" +
                "                                    </div>", this.is_right ? "right-msg" : "left-msg", this.getBackgroundUrl(), this.getUser().getUsername(), this.getSended_at(), this.getContent());
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
