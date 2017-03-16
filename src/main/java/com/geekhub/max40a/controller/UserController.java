package com.geekhub.max40a.controller;

import com.geekhub.max40a.model.User;
import com.geekhub.max40a.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/host/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{id}")
    public User getUserBuId(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewUser(String firstName, String email, String password) {
        User user = new User();
        user.setName(firstName);
        user.setEmail(email);
        user.setPassword(password);

        userService.addUser(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateUser(@PathVariable Integer id,
                           @RequestParam String firstName,
                           @RequestParam String email,
                           @RequestParam String password) {
        User user = new User();
        user.setId(id);
        user.setName(firstName);
        user.setEmail(email);
        user.setPassword(password);
        userService.updateUser(user);
    }
}