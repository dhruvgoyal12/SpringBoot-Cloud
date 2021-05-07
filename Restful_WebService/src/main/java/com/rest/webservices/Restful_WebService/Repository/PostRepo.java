package com.rest.webservices.Restful_WebService.Repository;

import com.rest.webservices.Restful_WebService.Bean.Post;
import com.rest.webservices.Restful_WebService.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {


}
