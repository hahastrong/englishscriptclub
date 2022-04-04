package com.englishscriptclub.blog.controller;

import com.englishscriptclub.blog.dao.UserRepository;
import com.englishscriptclub.blog.entity.User;
import com.englishscriptclub.blog.entity.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String userInfo(@RequestParam(name = "id", defaultValue = "0") int id,
                           @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable =  PageRequest.of(page, 10, Sort.Direction.DESC, "updateTime");
//        List<User> users = userRepository.findUsersByIdExistsOrderByUpdateTimeDescUpdateTime(pageable);

        Page<User> users = userRepository.findAll(pageable);
        User user = users.getContent().get(0);
        if (id != 0) {
            user = userRepository.findUserById(id);
        }

        model.addAttribute("users", users);
        model.addAttribute("user", user);
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("nextPage", page+1);
        model.addAttribute("prePage", page>0?page-1:0);
        return "userInfo";
    }

    @PostMapping("/user")
    public String saveUser(Model model, UserForm user) {
        System.out.println(user);
        User u = userRepository.findUserById(user.getId());
        u.setVip(user.getVip());
        System.out.println(u);
        userRepository.save(u);
        model.addAttribute("user", u);
        Pageable pageable =  PageRequest.of(0, 10, Sort.by("updateTime").descending());
        Page<User> users = userRepository.findAll(pageable);
        model.addAttribute("users", users);
        return "userInfo";
    }
}
