package com.geekhub.max40a.repository;

import com.geekhub.max40a.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

    User findById(Integer id);

    void delete(Integer id);

    void saveOrUpdateUser(User user);
}