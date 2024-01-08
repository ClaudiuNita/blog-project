package com.example.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapi.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
