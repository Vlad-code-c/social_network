package com.example.social_network;

import com.example.social_network.entities.Users;
import com.example.social_network.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

@Controller
public class MainController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/main")
    public String main(Model model){
        return "main_page";
//        return "main";
    }

    @GetMapping("/")
    public String mainM(Model model){
        return "main";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "auth/signup";
    }

    @GetMapping("/login")
    public String logIn(){
        return "auth/login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("name", "Vladz");
        model.addAttribute("id", "@Vladz5464");
        model.addAttribute("joined", "Joined September 18, 2020");
        model.addAttribute("avatar", "https://yt3.ggpht.com/-Ev00jf2SDp0/AAAAAAAAAAI/AAAAAAAAAAA/K28gnaebNfU/s900-c-k-no-rj-c0xffffff/photo.jpg");

        return "profile";
    }


    @PostMapping("/addUser")
    public @ResponseBody String addNewUser (@RequestParam(required = true) String name,
                                            @RequestParam(defaultValue = "https://yt3.ggpht.com/-Ev00jf2SDp0/AAAAAAAAAAI/AAAAAAAAAAA/K28gnaebNfU/s900-c-k-no-rj-c0xffffff/photo.jpg") String bio,
                                            @RequestParam(defaultValue = "") String profile_photo_url,
                                            @RequestParam(required = true) Date birthday,
                                            @RequestParam String email,
                                            @RequestParam(required = true) String password
    ){


        Users user = new Users();
        user.setNickname(name + user.getId());
        user.setName(name);
        user.setBio(bio);
        user.setProfile_photo_url(profile_photo_url);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setPassword(password);
        user.setIs_active(true);
        user.setIs_blocked(false);

        Date date = new Date(System.currentTimeMillis());
        user.setCreated_at(date);
        user.setUpdated_at(date);

        usersRepository.save(user);

        return "Saved";
    }





}
