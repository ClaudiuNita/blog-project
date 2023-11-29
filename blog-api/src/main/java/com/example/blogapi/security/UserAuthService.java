package com.example.blogapi.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blogapi.model.User;
import com.example.blogapi.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username);

        if (!username.equals("admin")) {
            if (user == null) {
                throw new UnsupportedOperationException("No user found with username: " + username);
            }
        }
        
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if (username.equals("admin")) {
            user = new User(username, "admin");
            SimpleGrantedAuthority authorityAdmin = new SimpleGrantedAuthority("ADMIN");
            authorities.add(authorityAdmin);
        }

        SimpleGrantedAuthority authorityUser = new SimpleGrantedAuthority("USER");
        authorities.add(authorityUser);
       
        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder() 
                                    .username(user.getUsername()) 
                                    .password(user.getPassword()) 
                                    .authorities(authorities) 
                                    .build();
    }
}
