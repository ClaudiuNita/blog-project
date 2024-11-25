package com.example.blogapi.DTO;

import java.time.LocalDateTime;

public class PostCommentDTO {
    
    private Long id;
    private String comment;
    private LocalDateTime localDateTime;
    private Long postId;
    private String user;
    
    public PostCommentDTO(Long id, String comment, LocalDateTime localDateTime, Long postId, String user) {
        this.id = id;
        this.comment = comment;
        this.localDateTime = localDateTime;
        this.postId = postId;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    } 

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
