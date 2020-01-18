package com.example.technoarenaapp;

public class user {
    public String username;
    public String email;

    public user() {

    }

    public String getUsername() {
        return username;
    }

    public user(String username, String email) {
        this.username = username;
        this.email = email;
    }
}