package com.geekhub.max40a.services;

import com.geekhub.max40a.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findById(Integer id);

    void addUser(User user);

    void deleteUser(Integer id);

    void updateUser(User user);
}
