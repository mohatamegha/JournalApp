package com.example.journalApp.Controller;

import com.example.journalApp.api_response.WeatherAPIResponse;
import com.example.journalApp.entities.Users;
import com.example.journalApp.repository.UserRepoImpl;
import com.example.journalApp.services.UserService;
import com.example.journalApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    WeatherService weatherService;

//    @GetMapping
//    public ResponseEntity<?> getAllUsers(){
//        List<Users> allUsers = userService.getAllUsers();
//        if(allUsers!=null && !allUsers.isEmpty())
//            return new ResponseEntity<>(allUsers, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @GetMapping("/id/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable ObjectId id){
//        Users user = userService.getUserById(id);
//        if(user != null)
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping()
    public ResponseEntity<Users> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<?> updateUserById(@RequestBody Users newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users currUser = userService.findByUsername(username);
        currUser.setUsername(newUser.getUsername());
        currUser.setPassword(newUser.getPassword());
        userService.saveNewUser(currUser);
        return new ResponseEntity<>(currUser, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<String> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String message = "Hello, "+username;
        String greeting = "";
        WeatherAPIResponse weatherAPIResponse = weatherService.getWeather("Kolkata");
        if(weatherAPIResponse != null){
            greeting = " Weather feels like "+weatherAPIResponse.getCurrent().getFeelslike();
            message += greeting;
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Autowired
    UserRepoImpl userRepoImpl;
    @GetMapping("/megha")
    public ResponseEntity<Users> getMegha(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Users> user = userRepoImpl.getUsersForSA();
        return new ResponseEntity<>(user.getFirst(), HttpStatus.OK);
    }
}