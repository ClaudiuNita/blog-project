package com.example.blogapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.blogapi.DTO.PostDTO;
import com.example.blogapi.DTO.UserDTO;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;

@Service
public class PostService {
    
    @Resource
    private PostRepository postRepository;
    @Resource
    private UserRepository userRepository;

    public List<PostDTO> getAllPosts() {

        List<Post> posts = postRepository.findAll();
        List<PostDTO> postsDTO = new ArrayList<>();
        posts.forEach(post -> 
            postsDTO.add(
                new PostDTO(post.getId(),
                            post.getContent(),
                            post.getLocalDateTime(),
                            new UserDTO(post.getAuthor().getId(),
                                        post.getAuthor().getUsername()))
            )
        );
        return postsDTO;
    }

    public void savePost(String content, String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NoSuchElementException(username);
        }
        Post post = new Post(content);
        post.setAuthor(user);
        postRepository.save(post);
    }
}
