package com.example.social_network.service;


import com.example.social_network.entity.MyUser;
import com.example.social_network.entity.Role;
import com.example.social_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepo.findByUsername(username);
        if (myUser == null){
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        UserDetails user = User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(Role.USER.toString())
                .build();


        return user;
    }


    public boolean saveUser(MyUser user){
        MyUser userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            return false;
        }

        Set<Role> role = Collections.singleton(Role.USER);
        if (user.getRoles().contains(Role.ADMIN))
            role = Collections.singleton(Role.ADMIN);

        user.setRoles(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setBio("");
        user.setIs_active(true);
        user.setCreated_at(new Date(System.currentTimeMillis()));
        user.setUpdated_at(user.getCreated_at());
        user.setProfile_photo_url("https://cdn.iconscout.com/icon/free/png-256/avatar-370-456322.png");
        userRepo.save(user);



        return true;

    }



}
