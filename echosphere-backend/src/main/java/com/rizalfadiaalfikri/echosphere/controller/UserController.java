package com.rizalfadiaalfikri.echosphere.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.req.UsersDto;
import com.rizalfadiaalfikri.echosphere.models.res.Response;
import com.rizalfadiaalfikri.echosphere.services.UserService;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Value("${application.version}")
    private String version;

    @Autowired
    private UserService userService;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Users users = userService.findUserById(id);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(200)
                        .message("Data Found")
                        .success(true)
                        .version(version)
                        .data(users)
                        .build());
    }

    @GetMapping("/users")
    public ResponseEntity<Response> findByEmail(@RequestParam("email") String email) {
        Users users = userService.findUserByEmail(email);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(200)
                        .message("Data Found")
                        .success(true)
                        .version(version)
                        .data(users)
                        .build());
    }

    @PutMapping("/users/follow/{userId1}/{usersId2}")
    public ResponseEntity<Response> followuserHandler(@PathVariable("userId1") Long userId1,
            @PathVariable("usersId2") Long userId2) {
        Users users = userService.followuser(userId1, userId2);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(200)
                        .message("Data success updated")
                        .success(true)
                        .version(version)
                        .data(users)
                        .build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Response> updateUser(@RequestBody UsersDto dto, @PathVariable("id") Long id) {
        Users users = userService.updateUser(dto, id);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(200)
                        .message("Data success updated")
                        .success(true)
                        .version(version)
                        .data(users)
                        .build());
    }

    @GetMapping("/users/search")
    public ResponseEntity<Response> searchUsers(@RequestParam("query") String query) {
        List<Users> users = userService.searchUser(query);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(200)
                        .message("Data Found")
                        .success(true)
                        .version(version)
                        .data(users)
                        .build());
    }

}
