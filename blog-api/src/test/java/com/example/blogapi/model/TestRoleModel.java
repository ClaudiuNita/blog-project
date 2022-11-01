package com.example.blogapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRoleModel {

    Role role = new Role();
    User user = new User();

    @Test
    public void testAddUsersInRole(){

        user.setId(1L);
        user.setEmailAddress("claud@tremend.com");
        user.setPassword("ccc");

        role.setId(1L);
        role.setRole("normal");

        role.addUser(user);

        Assertions.assertEquals("claud@tremend.com", role.getUsers().get(0).getEmailAddress());
    }
}
