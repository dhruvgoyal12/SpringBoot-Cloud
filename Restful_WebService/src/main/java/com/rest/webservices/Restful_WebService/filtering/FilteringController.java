package com.rest.webservices.Restful_WebService.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1", "value2", "value3");
    }
    @GetMapping("/filtering/all")
    public List<SomeBean> retrieveListSomeBean(){
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),new SomeBean("value1", "value2", "value3"));
    }
}
