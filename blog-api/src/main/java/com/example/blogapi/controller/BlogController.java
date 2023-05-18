package com.example.blogapi.controller;

import com.example.blogapi.DTO.UserDTO;
import com.example.blogapi.model.User;
import com.example.blogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


//@Controller
@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {


    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String blogPage(){

        return "blog";
    }

    @GetMapping("/getUsers")
    public List<UserDTO> getAllUsers(){

        List<UserDTO> users = blogService.getAllUsers();
        return users;
    }

    @GetMapping("/getUser/{id}")
    public UserDTO getUserById(@PathVariable Long id) {

        return blogService.getUserById(id);
    }

    @GetMapping("/add")
    public String add(Model model){

        List<User> users = blogService.addElementsInUserRoleTable();
        model.addAttribute("userrole", users);

        return "blog";
    }

    @PostMapping ("/postUser")
    public void addUser(@RequestBody String email){

        System.out.println(email);
        blogService.addUser(email);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UserDTO user){

        System.out.println(user.getId() + "" + user.getEmail());
        blogService.updateUserById(user.getId(), user.getEmail());
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody Long id){

        blogService.deleteUserById(id);
    }
}
