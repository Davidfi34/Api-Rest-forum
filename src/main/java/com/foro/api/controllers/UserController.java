package com.foro.api.controllers;

import com.foro.api.models.user.DTO.UserRegistration;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @PostMapping
    public void userCreate(@RequestBody UserRegistration userRegistration){
        System.out.println(userRegistration);
    }

}
