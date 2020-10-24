package com.example.social_network.components;

import com.example.social_network.entity.Message;
import com.example.social_network.entity.MyUser;
import com.example.social_network.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private UserRepository userRepo;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null){
            logger.info("User Disconnected : " + username);

            Message message = new Message();


            MyUser user = userRepo.findByUsername(username);
            Long id;
            if (user == null) {
                user = new MyUser();
                user.setId(0l);
            }

            message.setSenderId(user.getId());

            messagingTemplate.convertAndSend("/topic/public", message);
        }
    }
}
