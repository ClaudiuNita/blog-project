package com.example.blogapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
                            post.getTitle(),
                            post.getContent(),
                            post.getLocalDateTime(),
                            new UserDTO(post.getAuthor().getId(),
                                        post.getAuthor().getUsername()))
            )
        );
        return postsDTO;
    }

    public void savePost(String title, String content, String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NoSuchElementException(username);
        }
        Post post = new Post(title, content);
        post.setAuthor(user);
        postRepository.save(post);
    }

    public void deletePost(Long id) {

        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("No post with id " + id);
        }
    }

    public void updatePost(PostDTO postDTO) {
        
        Optional<Post> post = postRepository.findById(postDTO.getId());
        if (post.isPresent()) {    
            post.get().setTitle(postDTO.getTitle());
            post.get().setContent(postDTO.getContent());
            postRepository.save(post.get());
        } else {
            throw new NoSuchElementException();
        }
    }
}
