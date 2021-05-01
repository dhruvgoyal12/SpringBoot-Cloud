package com.rest.webservices.Restful_WebService.controller;

import com.rest.webservices.Restful_WebService.Bean.User;
import com.rest.webservices.Restful_WebService.dao.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping(path = "users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userDaoService.save(user);
        // Getting the uri for the created user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser
                .getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
