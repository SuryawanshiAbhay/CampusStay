package com.example.campusstay;

public class UserModel {

    public String uid;
    public String phone;
    public String role;

    // Required empty constructor for Firebase
    public UserModel() {
    }

    public UserModel(String uid, String phone, String role) {
        this.uid = uid;
        this.phone = phone;
        this.role = role;
    }
}