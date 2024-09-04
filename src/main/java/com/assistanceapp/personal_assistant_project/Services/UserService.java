package com.assistanceapp.personal_assistant_project.Services;
import com.assistanceapp.personal_assistant_project.Model.User;
import com.assistanceapp.personal_assistant_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired

    private UserRepository userRepository;

    public User saveUser(User user){
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
        if(userRepository.existsById(id_user)){
            user.setId(id_user);
            return userRepository.save(user);
        }else{
            return null;
        }

    }

    }

    

