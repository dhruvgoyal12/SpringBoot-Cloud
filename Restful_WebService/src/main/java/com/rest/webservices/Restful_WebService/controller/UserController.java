package com.rest.webservices.Restful_WebService.controller;

import com.rest.webservices.Restful_WebService.Bean.User;
import com.rest.webservices.Restful_WebService.dao.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService = new UserDaoService();

    // retrieveAllUsers
    @GetMapping(path = "users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }

    //retrieveUser
    @GetMapping(path = "users/{id}")
    public User getUser(@PathVariable int id) {
        return userDaoService.findOne(id);
    }

    @PostMapping(path="users")
    public void createUser(@RequestBody User user){
        User savedUser = userDaoService.save(user);

    }
}
