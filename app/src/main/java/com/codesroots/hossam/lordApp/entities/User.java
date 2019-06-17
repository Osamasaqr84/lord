package com.codesroots.hossam.lordApp.entities;

public class User {

    private  String username,password,gender,birthdate,mobile;

    public User(String username, String password, String gender, String birthdate, String mobile) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthdate = birthdate;
        this.mobile = mobile;
    }

    public User(String username, String pass) {
        this.username = username;
        this.password = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
