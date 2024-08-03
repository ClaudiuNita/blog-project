package com.example.blogapi.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapi.DTO.UserDTO;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class UsernameController {

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getCurrentUsername(HttpServletRequest request) {

        if(request.getUserPrincipal() != null) {
            Principal principal = request.getUserPrincipal();
            return new UserDTO(principal.getName());
        } else {
            return null;
        }
    }
}
