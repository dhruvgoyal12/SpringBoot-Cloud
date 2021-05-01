package com.rest.webservices.Restful_WebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//Restful Web Services
//Social Media Application
//Retrieve all Users -GET /users
//Create a User - POST /users
//Retrieve one User - GET /users/{id}
//Delete a User - DELETE /users/{id}
//
//Retrieve all posts for a User - GET /users/{id}/posts
//Create a post for a User - POST /users/{id}/posts
//Retrieve details of a post -GET /users/{id}/posts/{post_id}


@SpringBootApplication

public class RestfulWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServiceApplication.class, args);
    }

}
