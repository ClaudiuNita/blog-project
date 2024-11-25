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

import com.example.blogapi.DTO.PostCommentDTO;
import com.example.blogapi.service.PostCommentService;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class PostCommentController {
    
    @Resource
    private PostCommentService postCommentService;

    @GetMapping("/comment/{id}")
    public PostCommentDTO getPostComment(@PathVariable Long id) {

        return postCommentService.getPostComment(id);
    }

    @GetMapping("/comment/post/{postId}")
    public List<PostCommentDTO> getPostCommentsByPostId(@PathVariable Long postId) {

        return postCommentService.getPostCommentsByPostId(postId);
    }

    @PostMapping("/comment")
    public void savePostComment(@RequestBody PostCommentDTO postCommentDTO) {

        postCommentService.savePostComment(postCommentDTO);
    }

    @PutMapping("/comment")
    public void updatePostComment(@RequestBody PostCommentDTO postCommentDTO) {

        postCommentService.updatePostComment(postCommentDTO);
    }

    @DeleteMapping("/comment/{id}")
    public void deletePostComment(@PathVariable("id") Long id) {

        postCommentService.deletePostComment(id);
    }
}
