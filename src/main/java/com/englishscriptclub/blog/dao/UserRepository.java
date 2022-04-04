package com.englishscriptclub.blog.dao;

import com.englishscriptclub.blog.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByIdExistsOrderByUpdateTimeDescUpdateTime(Pageable page);

    List<User> findAll();

    User findUserById(int id);

    User findUserByPhone(String phone);

    User findUserByOpenid(String openid);
}
