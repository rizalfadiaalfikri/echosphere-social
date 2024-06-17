package com.rizalfadiaalfikri.echosphere.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rizalfadiaalfikri.echosphere.models.entity.Comments;
import com.rizalfadiaalfikri.echosphere.models.entity.Reels;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.res.Response;
import com.rizalfadiaalfikri.echosphere.services.ReelsService;
import com.rizalfadiaalfikri.echosphere.services.UserService;

@RequestMapping("/api/v1")
@RestController
public class ReelsController {

    @Value("${application.version}")
    private String version;

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/reels/user/{userId}")
    public ResponseEntity<Response> createReels(
            @RequestBody Reels reels,
            @PathVariable("userId") Long userId) {

        Users reqUser = userService.findUserById(userId);
        Reels newReels = reelsService.createReels(reels, reqUser);

        return ResponseEntity.created(URI.create("")).body(
                Response.builder()
                        .code(201)
                        .message("Create reels has successfully")
                        .success(true)
                        .version(version)
                        .data(newReels)
                        .build());
    }

    @GetMapping("/reels")
    public ResponseEntity<Response> findAllReels() {
        List<Reels> newReels = reelsService.findAllReels();

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(201)
                        .message("Data Found")
                        .success(true)
                        .version(version)
                        .data(newReels)
                        .build());
    }

    @GetMapping("/reels/user/{userId}")
    public ResponseEntity<Response> findReelsByUserId(@PathVariable("userId") Long userId) {
        List<Reels> newReels = reelsService.findUsersReels(userId);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(201)
                        .message("Data Found")
                        .success(true)
                        .version(version)
                        .data(newReels)
                        .build());
    }

}
