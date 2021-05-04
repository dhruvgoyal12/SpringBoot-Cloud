package com.rest.webservices.Restful_WebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.ResourceBundle;

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

    @Bean
    public LocaleResolver localeResolver(){
        //SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

//    @Bean
//    public ResourceBundleMessageSource bundleMessageSource(){
//     ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//    messageSource.setBasename("messages");
//     return messageSource;
//    }
}
