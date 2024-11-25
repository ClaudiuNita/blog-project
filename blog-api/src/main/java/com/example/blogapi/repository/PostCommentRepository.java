package com.example.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapi.model.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
