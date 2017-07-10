/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NextIdService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Owner
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NextIdService userIdService;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User user){
        System.out.println("register");
        int nextId = userIdService.getNextUserId("User");
        user.setId(String.valueOf(nextId));
        userRepository.insert(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> find(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
}
