package com.example.blogapi.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapi.DTO.UserDetailsDTO;
import com.example.blogapi.service.UserDetailsService;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class UserDetailsController {
    
    @Resource
    private UserDetailsService userDetailsService;

    @GetMapping("/user-details/{id}")
    public UserDetailsDTO getUserDetailsById(@PathVariable Long id) {
        
        return userDetailsService.getUserDetailsById(id);
    }

    @GetMapping("/user-details")
    public Integer getUserDetailsId(@RequestParam(value = "username") String username) {
        
        return userDetailsService.getUserDetailsId(username);
    }

    @PutMapping("/user-details")
    public void updateUserDetails(@RequestBody UserDetailsDTO userDetailsDTO) {

        userDetailsService.updateUserDetails(userDetailsDTO);
    }
}
