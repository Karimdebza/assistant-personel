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

    @GetMapping("/{id_user}")
    public ResponseEntity<User> getUserById(@PathVariable Long id_user) {
         try {
        Optional<User> user = userService.getUserById(id_user);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }catch (Exception e) {
            // Log the exception
            System.err.println("Error retrieving user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
        }catch(Exception e){
            System.err.println("Error retrieving users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id_user}")
    public ResponseEntity<User> updateUser(@PathVariable Long id_user, @RequestBody User user) {
       try {
         User userUpdate = userService.updateUser(id_user, user);
         return userUpdate != null ? ResponseEntity.ok(userUpdate) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       } catch (Exception e) {
        System.err.println("Error updating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    
    @DeleteMapping("/{id_user}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id_user) {
        try {
            if(userService.getUserById(id_user).isPresent()){
                userService.deleteUser(id_user);
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    

    
}
