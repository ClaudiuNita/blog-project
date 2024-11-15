package com.example.blogapi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public void savePost(@RequestBody PostDTO postReq) {
        
        postService.savePost(postReq.getTitle(), postReq.getContent(), postReq.getAuthor().getUsername());
    }

    @PutMapping("/post")
    public void updatePost(@RequestBody PostDTO post) {
        
        postService.updatePost(post);
    }
    
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable("id") Long id) {

        postService.deletePost(id);
    }
}
