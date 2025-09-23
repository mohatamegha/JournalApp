package com.example.journalApp.services;

import com.example.journalApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Implementation here
        Users user = userService.findByUsername(username);
        if(user!=null){
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
//                    .authorities("USER") // You can set roles/authorities as needed
                    .build();
        }
        else{
            throw new RuntimeException("User not found");
        }
    }
}