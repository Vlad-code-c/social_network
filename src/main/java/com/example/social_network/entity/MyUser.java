package com.example.social_network.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    private String bio;
    private String profile_photo_url;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String email;



    private boolean is_active;
    private boolean is_blocked;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updated_at;


    public String getCustomId(){
        return this.getUsername().trim() + "@" +
                String.format("%04d", this.getId());
                //0 - pat with zeros
                //3 - minimum of 3
                //d - as a decimal integer
    }
    public String getCustomUsername(){
        if (username.length() >= 2)
            return this.username.substring(0,1).toUpperCase() +
                    this.username.substring(1);
        else
            return username.toUpperCase();
    }

    public String getCustomJoined(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getCreated_at());

        return "Joined " +
                calendar.get(Calendar.DAY_OF_MONTH) + " " +
                new SimpleDateFormat("MMM").format(calendar.getTime()) + ", " +
                calendar.get(Calendar.YEAR);
    }

    public boolean isAdmin(){
        if (this.getRoles().contains(Role.ADMIN))
            return true;
        return false;
    }

    public MyUser(){}

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfile_photo_url() {
        if (this.profile_photo_url == "" || this.profile_photo_url == null){
            return "https://www.shareicon.net/data/512x512/2016/07/10/119669_people_512x512.png";
        }
        return profile_photo_url;
    }

    public void setProfile_photo_url(String profile_photo_url) {
        this.profile_photo_url = profile_photo_url;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
