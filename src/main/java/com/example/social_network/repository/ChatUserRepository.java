package com.example.social_network.repository;

import com.example.social_network.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    List<ChatUser> findFirstByChat_ChatIdAndAndUser_Id(Long chatId, Long user_Id);
}
