package com.developermaker.dto;

public class User {
    private String nickname;

    public User() {}

    public User(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    @Override
    public String toString() {
        return "User{nickname='" + nickname + "'}";
    }
}

