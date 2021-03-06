package com.rest.webservices.Restful_WebService.controller;

import com.rest.webservices.Restful_WebService.Bean.User;
import com.rest.webservices.Restful_WebService.Exceptions.UserNotFoundException;
import com.rest.webservices.Restful_WebService.dao.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private MessageSource messageSource;
    // retrieveAllUsers
    @GetMapping(path = "users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }

    //retrieveUser
    @GetMapping(path = "users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

//        all-users, SERVER_PATH + "/users"
//                /retrieveAllUsers
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(path = "users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        // Getting the uri for the created user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser
                .getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "users/{id}")
    public User deleteUser(@PathVariable int id) {
        User user = userDaoService.deleteById(id);
        if (user == null) throw new UserNotFoundException("id-" + id);

        return user;
    }

    @GetMapping(path = "/users-int")
//    public String userInt(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
//        return messageSource.getMessage("good.morning.message", null, locale);
//    }
    public String userInt(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
