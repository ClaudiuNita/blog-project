package com.example.blogapi.controller;

import com.example.blogapi.DTO.UserDTO;
import com.example.blogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){

        return blogService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {

        return blogService.getUserById(id);
    }

    @PostMapping ("/user")
    public void addUser(@RequestBody String email){

        blogService.addUser(email);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserDTO user){

        blogService.updateUserById(user.getId(), user.getEmail());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestBody Long id){

        blogService.deleteUserById(id);
    }
}
