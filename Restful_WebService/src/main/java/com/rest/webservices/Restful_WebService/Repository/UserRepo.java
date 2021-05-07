package com.rest.webservices.Restful_WebService.Repository;

import com.rest.webservices.Restful_WebService.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
