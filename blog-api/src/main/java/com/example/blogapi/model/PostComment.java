package com.example.blogapi.model;

import javax.persistence.*;

@Entity
@Table(name = "POST_COMMENT")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private BlogPost post;
}
