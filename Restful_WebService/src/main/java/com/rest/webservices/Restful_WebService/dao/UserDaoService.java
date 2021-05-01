package com.rest.webservices.Restful_WebService.dao;

import com.rest.webservices.Restful_WebService.Bean.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<User>();
    private static int usersCount = 4;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Adaqq", new Date()));
        users.add(new User(3, "Adamqq", new Date()));
        users.add(new User(4, "Ada", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }


}
