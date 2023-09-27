package com.foro.api.controllers;


import com.foro.api.infra.security.JwtTokenData;
import com.foro.api.infra.security.TokenService;
import com.foro.api.models.user.DTO.UserAuthenticationData;
import com.foro.api.models.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticationUser(@RequestBody @Valid UserAuthenticationData userAuthenticationData ){

        Authentication authToken = new UsernamePasswordAuthenticationToken( userAuthenticationData.username(),
                userAuthenticationData.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JwtTokenData(JwtToken));

    }

}