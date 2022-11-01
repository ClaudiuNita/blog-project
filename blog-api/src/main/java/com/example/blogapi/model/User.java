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

//    @ManyToOne
//    @JoinColumn(name = "role")
//    private Role role;

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

    public List<Role> getRoles() {
        return roles;
    }

//    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USERS_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ROLES_ID", referencedColumnName = "id")
    )
    private List<Role> roles;

    public void addRole(Role role) {

        roles.add(role);
    }



    //not saved in the database
    @Transient
    private int numberOfLatestPosts;















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

    public int getNumberOfLatestPosts() {
        return numberOfLatestPosts;
    }

    public void setNumberOfLatestPosts(int numberOfLatestPosts) {
        this.numberOfLatestPosts = numberOfLatestPosts;
    }


}
