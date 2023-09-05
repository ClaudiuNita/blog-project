package com.example.blogapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

// NOT USED

// @Controller
// @CrossOrigin(origins = "http://localhost:4200")
// public class GetUserWithHTTPServletRequestController {

//     @RequestMapping(value = "/username", method = RequestMethod.GET)
//     @ResponseBody
//     public String currentUserName(HttpServletRequest request) {

//         if(request.getUserPrincipal() != null) {

//             Principal principal = request.getUserPrincipal();
//             return principal.getName();
//         } else {
//             return "null";
//         }  
//     }
// }