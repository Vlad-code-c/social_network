package com.example.social_network.repository;

import com.example.social_network.entity.Chat;
import com.example.social_network.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository  extends JpaRepository<Chat, Long> {
    Chat findByChatId(Long chat_id);

//    @Query("")
//    List<Long> findAllIdsByChatId(@Param("chat_id") Long chat_id);

}
