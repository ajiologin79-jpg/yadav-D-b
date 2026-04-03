package com.demo.controller;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired private UserRepository userRepository;

    @PostMapping("user/add")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("user/getUser")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("user/{email}")
    public Optional<User> getByEmail(@PathVariable String email){
        return userRepository.findById(email);
    }
}
