package com.example.blogapi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapi.DTO.PostDTO;
import com.example.blogapi.service.PostService;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    
    @Resource
    private PostService postService;

    @GetMapping("/posts")
    public List<PostDTO> getAllPosts() {

        return postService.getAllPosts();
    }

    @PostMapping("/post")
    public void savePost(@RequestBody String body) {

        String content = body.split("\"")[3];
        String username = body.split("\"")[7];
        postService.savePost(content, username);
    }
}
