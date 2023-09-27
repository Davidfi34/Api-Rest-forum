package com.foro.api.controllers;

import com.foro.api.models.user.DTO.UserRegistration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public void userCreate(@RequestBody UserRegistration userRegistration){
        System.out.println(userRegistration);
    }

}
