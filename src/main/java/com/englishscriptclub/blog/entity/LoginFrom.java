package com.englishscriptclub.blog.entity;

import lombok.Data;

@Data
public class LoginFrom {
    private String username;
    private String password;

    public LoginFrom(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginFrom() {}
}
