package com.example.social_network.repository;

import com.example.social_network.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);
    MyUser findFirstById(Long id);
}
