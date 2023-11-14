package com.example.blogapi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.blogapi.model.UserDetails;
import com.example.blogapi.repository.UserDetailsRepository;

@Service
public class UserDetailsService {
    
    @Resource
    private UserDetailsRepository userDetailsRepository;

    public UserDetails getUserDetailsById(Long id) {

        return userDetailsRepository.findById(id).get();
    }
}
