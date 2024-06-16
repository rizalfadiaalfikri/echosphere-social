package com.rizalfadiaalfikri.echosphere.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.req.LoginDto;
import com.rizalfadiaalfikri.echosphere.models.req.UsersDto;
import com.rizalfadiaalfikri.echosphere.models.res.LoginResponse;
import com.rizalfadiaalfikri.echosphere.models.res.Response;
import com.rizalfadiaalfikri.echosphere.services.AuthService;
import com.rizalfadiaalfikri.echosphere.services.UserService;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    @Value("${application.version}")
    private String version;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UsersDto dto) {
        Users users = userService.registerUser(dto);

        return ResponseEntity.created(URI.create("")).body(
                Response.builder()
                        .code(201)
                        .message("Data was successfully registered")
                        .success(true)
                        .version(version)
                        .data(users)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginDto dto) {
        LoginResponse response = authService.login(dto);

        return ResponseEntity.created(URI.create("")).body(
                Response.builder()
                        .code(201)
                        .message("Login has successfully")
                        .success(true)
                        .version(version)
                        .data(response)
                        .build());
    }

}
