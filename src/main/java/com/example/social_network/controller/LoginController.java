package com.example.social_network.controller;

import com.example.social_network.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/signup")
    public String signUp(Model model){
        System.out.println("FUCK YOU [signup]");
        model.addAttribute("userForm", new User());

        return "/signup";
    }

    @GetMapping("/login")
    public String logIn(){
        System.out.println("FUCK YOU [login]");
        return "/login";
    }


    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
        //User userFromDb = usersRepository.findByUsername(user.getUsername());

        System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE! [signup]");


//        if(userFromDb != null){
//            System.out.println("User Exists arleady");
//        }
//
//
//
//        Date date_ = new Date(System.currentTimeMillis());
//        user.setCreated_at(date_);
//        user.setUpdated_at(date_);
//
//        usersRepository.save(user);

        return "/login";
    }
//    @PostMapping("/signup")
//    public String addNewUser (@RequestParam(required = true) String username,
//                       @RequestParam(defaultValue = "") String bio,
//                       @RequestParam(defaultValue = "https://yt3.ggpht.com/-Ev00jf2SDp0/AAAAAAAAAAI/AAAAAAAAAAA/K28gnaebNfU/s900-c-k-no-rj-c0xffffff/photo.jpg") String profile_photo_url,
//                       @RequestParam(required = true) Date date,
//                       @RequestParam String email,
//                       @RequestParam(required = true) String password
//    ){
//
////        Users userFromDb = usersRepository.findByName(username);
////        System.out.println(userFromDb.getName());
////        if (userFromDb != null){
////            model.addAttribute("error", "User exists");
////            return "/signup";
////        }
//
//        System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE!");
//        Users user = new Users();
//        user.setName(username);
//        user.setBio(bio);
//        user.setProfile_photo_url(profile_photo_url);
//        user.setBirthday(date);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setIs_active(true);
//        user.setIs_blocked(false);
//
//        Date date_ = new Date(System.currentTimeMillis());
//        user.setCreated_at(date_);
//        user.setUpdated_at(date_);
//
//        usersRepository.save(user);
//
//        return "main";
//    }

    @PostMapping("/login")
    public String loginUser(User user){

        System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE! [login]");



        return "/main";
    }

    @GetMapping("*")
    public String v(){

        System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE! [any]");

        return "main_page_auth";
    }

}
