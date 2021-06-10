package com.zjut.ida.achievement_recommend_system;

//用户信息
public class UserBean {
    private int user_id;
    private String username;
    private String password;
    private String gender;
    private String likes;
    private String phoneNum;

    public UserBean() {
    }

    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getLikes() {
        return likes;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setUserId(int userId) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
