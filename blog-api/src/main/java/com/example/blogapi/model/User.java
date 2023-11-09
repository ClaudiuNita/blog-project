package com.example.blogapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailAddress;
    private String password;

    @Transient
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private UserDetails userDetails;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<BlogPost> blogPosts;

    public User(String email, String password) {
        this.emailAddress = email;
        this.password = password;
    }

    public User(String email) {
        this.emailAddress = email;
    }

    //-----------------------------------------------------------------
    public User() {
    }

    public User(Long id, String emailAddress, String password) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }
}
