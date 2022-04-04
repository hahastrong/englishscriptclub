package com.englishscriptclub.blog.controller;

import com.englishscriptclub.blog.dao.UserRepository;
import com.englishscriptclub.blog.entity.LoginFrom;

import com.englishscriptclub.blog.entity.User;
import com.englishscriptclub.blog.logic.WXLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RequestMapping
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private WXLogic wxLogic;

    @GetMapping("/login")
    public String getLogin(HttpServletRequest http,
                           @RequestParam(value = "username", defaultValue = "") String openid,
                           Model model) {
        String ua = http.getHeader("User-Agent");
        log.info("us: {}", ua);
        LoginFrom ll = wxLogic.getLoginInfo(ua, openid);
        model.addAttribute("loginForm", wxLogic.getLoginInfo(ua, openid));
        if (ll.getPassword() == null ) {
            return "login";
        }
        return "loginForWX";
    }

//    @PostMapping("/login")
//    public String doLogin(LoginFrom form, Model model) {
//        return "redirect:/audio";
//    }

}
