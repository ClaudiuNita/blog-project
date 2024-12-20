package com.example.blogapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.blogapi.DTO.UserDTO;
import com.example.blogapi.model.User;
import com.example.blogapi.model.UserDetails;
import com.example.blogapi.repository.UserDetailsRepository;
import com.example.blogapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserDetailsRepository userDetailsRepository;

    public List<UserDTO> getAllUsers() {

        List<User> users = new ArrayList<>(userRepository.findAll());
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user:users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());

            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    public UserDTO getUserById(Long id) {

            User user = userRepository.findById(id).get();

            return new UserDTO(user.getId(), user.getEmail(), user.getPassword());
    }

    public UserDTO getUserByUsername(String username) {

        User user = userRepository.findByUsername(username);

        if (username.replaceAll("[0-9]", "").isEmpty()) {
            throw new NumberFormatException();
        }
        if (user == null) {
            throw new NoSuchElementException();
        }

        return new UserDTO(user.getId(), user.getEmail(), user.getPassword());
}

    public void addUserByEmail(String email) {

        User user = new User(email);
        UserDetails userDetails = new UserDetails(email);

        userDetails.setUser(user);
        user.setUserDetails(userDetails);

        userRepository.save(user);
    }

    public void addUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        UserDetails userDetails = new UserDetails(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());

        userDetails.setUser(user);
        user.setUserDetails(userDetails);

        userRepository.save(user);
    }

    public void updateUserById(Long id, String email) {

        User user = userRepository.getReferenceById(id);
        user.setEmail(email);

        UserDetails userDetails = user.getUserDetails();
        userDetails.setEmail(email);

        userRepository.saveAndFlush(user);
    }

    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }
}
