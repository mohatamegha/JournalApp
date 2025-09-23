package com.example.journalApp.services;

import com.example.journalApp.entities.Users;
import com.example.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
    public Users getUserById(ObjectId id){
        return userRepo.findById(id).orElse(null);
    }

    public List<Users> getAllUsers(){
        List<Users> allUsers = userRepo.findAll();
        return allUsers;
    }

    public void saveNewUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepo.save(user);
    }

    public void saveUser(Users user){
        userRepo.save(user);
    }

    public boolean deleteUserById(ObjectId id){
        if(getUserById(id) != null){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteUserByUsername(String username){
        userRepo.deleteByUsername(username);
    }

    public Users findByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
