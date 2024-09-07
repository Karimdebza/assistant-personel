package com.assistanceapp.personal_assistant_project.Services;
import com.assistanceapp.personal_assistant_project.Model.User;
import com.assistanceapp.personal_assistant_project.repository.UserRepository;

import io.micrometer.common.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id_user){
        return userRepository.findById(id_user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id_user){
        userRepository.deleteById(id_user);
    }

    public User updateUser(Long id_user, User user){

        User existingUser = userRepository.findById(id_user).orElseThrow(() -> new RuntimeException("User not found"));

        Optional.ofNullable(user.getPassword()).filter(StringUtils::isBlank).ifPresent(password -> existingUser.setPassword(passwordEncoder.encode(password)));
        Optional.ofNullable(user.getEmail()).filter(StringUtils::isBlank).ifPresent(existingUser::setEmail);
        Optional.ofNullable(user.getFirstName()).filter(StringUtils::isBlank).ifPresent(existingUser::setFirstName);
        Optional.ofNullable(user.getLastName()).filter(StringUtils::isNotBlank).ifPresent(existingUser::setLastName);
        Optional.ofNullable(user.getUsername()).filter(StringUtils::isNotBlank) .ifPresent(existingUser::setUsername);

        return userRepository.save(existingUser);

        }


    
}
