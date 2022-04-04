package com.englishscriptclub.blog.logic;

import com.englishscriptclub.blog.dao.UserRepository;
import com.englishscriptclub.blog.entity.LoginFrom;
import com.englishscriptclub.blog.entity.User;
import com.englishscriptclub.blog.entity.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WXLogic {
    @Autowired
    private UserRepository userRepository;

    @Value("${ua.wechat}")
    private String uaType;

    public User updateUserPhone(User user){
        user.setUpdateTime(new Date());
        user = userRepository.save(user);
        return user;
    }

    public LoginFrom getLoginInfo(String ua, String openid) {
        if (ua.contains(uaType) && !openid.isEmpty()) {
            User user = userRepository.findUserByPhone(openid);
            if (user != null) {
                return new LoginFrom(user.getUsername(), user.getPassword());
            }
        }
        return new LoginFrom();
    }
}
