package com.example.blogapi.controller;

import org.springframework.web.bind.annotation.*;

import com.example.blogapi.util.Text;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class UsernameController {

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public Text getCurrentUsername(HttpServletRequest request) {

        if(request.getUserPrincipal() != null) {

            Principal principal = request.getUserPrincipal();
            return new Text(principal.getName());
        } else {
            return new Text("null");
        }
    }
}
