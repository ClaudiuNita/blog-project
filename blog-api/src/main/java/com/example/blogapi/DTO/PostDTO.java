package com.example.blogapi.DTO;

import java.time.LocalDateTime;

public class PostDTO {
    
    private Long id;
    private String content;
    private LocalDateTime localDateTime;
    private UserDTO author;

    public PostDTO(Long id, String content, LocalDateTime localDateTime, UserDTO author) {
        this.id = id;
        this.content = content;
        this.localDateTime = localDateTime;
        this.author = author;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "PostDTO [id=" + id + ", content=" + content + ", localDateTime=" + localDateTime + ", author=" + author
                + "]";
    }
}
