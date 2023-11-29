package com.example.blogapi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.blogapi.DTO.UserDetailsDTO;
import com.example.blogapi.model.User;
import com.example.blogapi.model.UserDetails;
import com.example.blogapi.repository.UserDetailsRepository;

@Service
public class UserDetailsService {
    
    @Resource
    private UserDetailsRepository userDetailsRepository;

    public UserDetailsDTO getUserDetailsById(Long id) {

        UserDetails userDetails = userDetailsRepository.findById(id).get();

        return new UserDetailsDTO(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), 
                userDetails.getPassword(), userDetails.getAge(), userDetails.getGender(), userDetails.getCountry());
    }

    public void updateUserDetails(UserDetailsDTO userDetailsDTO) {
        
        UserDetails userDetailsToUpdate = userDetailsRepository.findById(userDetailsDTO.getId()).get();
        userDetailsToUpdate.setAge(userDetailsDTO.getAge());
        userDetailsToUpdate.setCountry(userDetailsDTO.getCountry());
        userDetailsToUpdate.setEmail(userDetailsDTO.getEmail());
        userDetailsToUpdate.setGender(userDetailsDTO.getGender());
        userDetailsToUpdate.setPassword(userDetailsDTO.getPassword());
        userDetailsToUpdate.setUsername(userDetailsDTO.getUsername());

        User userToUpdate = userDetailsToUpdate.getUser();
        userToUpdate.setEmail(userDetailsDTO.getEmail());
        userToUpdate.setPassword(userDetailsDTO.getPassword());

        userDetailsRepository.saveAndFlush(userDetailsToUpdate);
    }
}
