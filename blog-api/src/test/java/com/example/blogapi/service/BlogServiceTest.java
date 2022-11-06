package com.example.blogapi.service;

import com.example.blogapi.model.User;
import com.example.blogapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class BlogServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    BlogService blogService;


    @Test
    @ExtendWith(MockitoExtension.class)
    void getAllUsersTest() {

        User user0 = new User();
        User user1 = new User();

        user0.setEmailAddress("salut0");
        user1.setEmailAddress("salut1");

        List<User> users = Arrays.asList(user0, user1);

        when(userRepository.findAll()).thenReturn(users);

        List<User> expectedUsers = Arrays.asList(user0, user1);
        //List<User> resultUsers = blogService.getAllUsers();

        //Assertions.assertTrue(resultUsers.containsAll(expectedUsers) && expectedUsers.containsAll(resultUsers));
        //Assertions.assertTrue(resultUsers.equals(expectedUsers));
        //Assertions.assertEquals(resultUsers, expectedUsers);
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void getUserByIdTest(){

        User user = new User();
        user.setId(1L);
        user.setEmailAddress("claudiu@tremend.com");

        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Assertions.assertTrue(blogService.getUserById(1L).equals(userOptional));

    }
}
