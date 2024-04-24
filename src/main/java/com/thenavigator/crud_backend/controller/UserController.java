package com.thenavigator.crud_backend.controller;


import com.thenavigator.crud_backend.exception.UserNotFoundExeption;
import com.thenavigator.crud_backend.model.User;
import com.thenavigator.crud_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5174")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    User getUserByuserId(@PathVariable Long userId){
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundExeption(userId));
    }

    @PutMapping("/user/{userId}")
    User updateUser(@RequestBody User newUser, @PathVariable Long userId){
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundExeption(userId));
    }

    @DeleteMapping("/user/{userId}")
    String deleteUser(@PathVariable Long userId){
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundExeption(userId);
        }

        userRepository.deleteById(userId);
        return "User with userId " + userId + " has been deleted successfully" ;
    }
}
