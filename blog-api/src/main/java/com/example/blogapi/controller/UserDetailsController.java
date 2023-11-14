package com.example.blogapi.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapi.model.UserDetails;
import com.example.blogapi.service.UserDetailsService;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class UserDetailsController {
    
    @Resource
    private UserDetailsService userDetailsService;

    @GetMapping("/user-details/{id}")
    public UserDetails getUserDetailsById(@PathVariable Long id) {
        
        return userDetailsService.getUserDetailsById(id);
    }

}
