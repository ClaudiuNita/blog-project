package com.example.blogapi.repository;

import com.example.blogapi.model.User;
import com.example.blogapi.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findByUsername(String username);
}
