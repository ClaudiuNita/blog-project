package com.example.blogapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> {
                                requests
                                        .antMatchers("/blog/username").permitAll()
                                        .antMatchers("/blog/user").hasRole("ADMIN")
                                        .antMatchers("/blog/user-details").hasRole("ADMIN")
                                        .anyRequest().authenticated();
                                }
                )
                .formLogin()
                .defaultSuccessUrl("http://localhost:4200/acasa", true)
                .and()
                .logout()
                .logoutSuccessUrl("http://localhost:4200/acasa")
                .and().csrf().disable()
                .headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userSecurityDetailsService() {
        UserDetails[] user = {
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build(),
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build()
                    };

        return new InMemoryUserDetailsManager(user);
    }
}