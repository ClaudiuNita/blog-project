package com.example.blogapi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.blogapi.DTO.PostCommentDTO;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.PostComment;
import com.example.blogapi.model.User;
import com.example.blogapi.repository.PostCommentRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;

@Service
public class PostCommentService {
    
    @Resource
    private PostCommentRepository postCommentRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private PostRepository postRepository;

    public PostCommentDTO getPostComment(Long id) {

        Optional<PostComment> postCommentOptional = postCommentRepository.findById(id);
        PostComment postComment;
        if (postCommentOptional.isPresent()) {
            postComment = postCommentOptional.get();
        } else {    
            throw new NoSuchElementException("No post comment with id: " + id);
        }

        return new PostCommentDTO(postComment.getId(), 
                                  postComment.getComment(), 
                                  postComment.getLocalDateTime(), 
                                  postComment.getPost().getId(), 
                                  postComment.getUser().getUsername());
    }

    public List<PostCommentDTO> getPostCommentsByPostId(Long postId) {

        List<PostComment> postComments = postCommentRepository.findAll(); 
        List<PostCommentDTO> postCommentDTOs = postComments.stream()
                                                           .filter(comment -> comment.getPost().getId() == postId)
                                                           .map(comment -> new PostCommentDTO(
                                                                comment.getId(), comment.getComment(),
                                                                comment.getLocalDateTime(), postId, 
                                                                comment.getUser().getUsername()))
                                                           .collect(Collectors.toList());
        if (postCommentDTOs.isEmpty()) {
            throw new NoSuchElementException("No comments on postId: " + postId);
        } 

        return postCommentDTOs;
    }

    public void savePostComment(PostCommentDTO postCommentDTO) {

        User user = userRepository.findByUsername(postCommentDTO.getUser());
        Optional<Post> postOptional = postRepository.findById(postCommentDTO.getPostId());
        Post post;
        if (user == null) {
            throw new NoSuchElementException("No user with username: " + postCommentDTO.getUser());
        }
        if (postOptional.isPresent()) {
            post = postOptional.get();
        } else {
            throw new NoSuchElementException("No post with post id: " + postCommentDTO.getPostId());
        }

        PostComment postComment = new PostComment(postCommentDTO.getComment());
        postComment.setUser(user);
        postComment.setPost(post);
        postCommentRepository.save(postComment);
    }

    public void updatePostComment(PostCommentDTO postCommentDTO) {

        Optional<PostComment> postCommentOptional = postCommentRepository.findById(postCommentDTO.getId());
        if (postCommentOptional.isPresent()) {
            postCommentOptional.get().setComment(postCommentDTO.getComment());
            postCommentRepository.save(postCommentOptional.get());
        } else {
            throw new NoSuchElementException("No post comment with id: " + postCommentDTO.getId());
        }
    }

    public void deletePostComment(Long id) {

        Optional<PostComment> postComment = postCommentRepository.findById(id);
        if (postComment.isPresent()) {
            postCommentRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("No post comment with id: " + id);
        }
    }
}
