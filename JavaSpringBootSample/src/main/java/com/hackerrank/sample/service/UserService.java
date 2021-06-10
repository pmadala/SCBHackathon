package com.hackerrank.sample.service;

import java.util.List;

import com.hackerrank.sample.model.User;

public interface UserService {
    User getUserById(String id);

    List<User> getAllUsers();

    void deleteById(String id);

    void create(User user);

    void  update(String id, User user);
}
