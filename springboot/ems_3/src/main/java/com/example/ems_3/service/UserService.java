package com.example.ems_3.service;

import com.example.ems_3.entity.User;

public interface UserService {
    void reg(String username, String password);

    User login(String username, String password);

    User findById(Integer id);

    /*User*/void changeInfo(User user);

    User findByUsername(String username);

    void changeAvatar(Integer uid,String uimg);

    String findUimgByUid(Integer uid);
}