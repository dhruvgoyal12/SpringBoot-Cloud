package com.rest.webservices.Restful_WebService.controller;

import com.rest.webservices.Restful_WebService.Bean.Post;
import com.rest.webservices.Restful_WebService.Bean.User;
import com.rest.webservices.Restful_WebService.Exceptions.UserNotFoundException;
import com.rest.webservices.Restful_WebService.Repository.PostRepo;
import com.rest.webservices.Restful_WebService.Repository.UserRepo;
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
import java.util.Optional;

@RestController
public class UserJpaController {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private MessageSource messageSource;
    // retrieveAllUsers
    @GetMapping(path = "jpa/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    //retrieveUser
    @GetMapping(path = "jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

//        all-users, SERVER_PATH + "/users"
//                /retrieveAllUsers
        EntityModel<User> resource = EntityModel.of(user.get());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(path = "jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepo.save(user);
        // Getting the uri for the created user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser
                .getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
       userRepo.deleteById(id);
//        if (user == null) throw new UserNotFoundException("id-" + id);
//
//        return user;
    }

    @GetMapping(path = "jpa/users-int")
//    public String userInt(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
//        return messageSource.getMessage("good.morning.message", null, locale);
//    }
    public String userInt(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }


    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsersPost(@PathVariable int id){
        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent()) throw new UserNotFoundException("id-"+ id);

        return user.get().getPosts();
    }


    @PostMapping(path = "jpa/users/{id}/posts")
    public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody Post post) {

        Optional<User> userOptional = userRepo.findById(id);

        if (!userOptional.isPresent()) throw new UserNotFoundException("id-"+ id);

        User user = userOptional.get();
        post.setUser(user);
        postRepo.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post
                .getId()).toUri();

       return ResponseEntity.created(location).build();
    }

}
