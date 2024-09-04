package com.assistanceapp.personal_assistant_project.Controllers;

import com.assistanceapp.personal_assistant_project.Model.User;
import com.assistanceapp.personal_assistant_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

@PostMapping("/create")

public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    } catch (Exception e) {
       
        System.err.println("Error creating user: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    
}
