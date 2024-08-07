package com.example.blogapi.controller;

import com.example.blogapi.DTO.UserDTO;
import com.example.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService blogService) {
        this.userService = blogService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable String id) {

        return userService.getUserById(Long.parseLong(id));
    }

    @GetMapping("/user-username")
    public UserDTO getUserByUsername(@RequestParam(value = "username") String username) {

        return userService.getUserByUsername(username);
    }

    @PostMapping ("/user")
    public void addUserByEmail(@RequestBody String email) {

        userService.addUserByEmail(email);
    }

    @PostMapping ("/new-user")
    public void addUser(@RequestBody UserDTO user) {

        userService.addUser(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserDTO user) {

        userService.updateUserById(user.getId(), user.getEmail());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestBody Long id) {

        userService.deleteUserById(id);
    }
}
