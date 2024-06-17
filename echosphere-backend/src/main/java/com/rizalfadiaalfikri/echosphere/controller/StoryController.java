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

import com.rizalfadiaalfikri.echosphere.models.entity.Reels;
import com.rizalfadiaalfikri.echosphere.models.entity.Stories;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.res.Response;
import com.rizalfadiaalfikri.echosphere.services.StoryService;

@RequestMapping("api/v1")
@RestController
public class StoryController {

    @Value("${application.version}")
    private String version;

    @Autowired
    private StoryService storyService;

    @PostMapping("/story/user/{userId}")
    public ResponseEntity<Response> createStories(
            @RequestBody Stories stories,
            @PathVariable("userId") Long userId) {

        Stories newStories = storyService.createStories(stories, userId);

        return ResponseEntity.created(URI.create("")).body(
                Response.builder()
                        .code(201)
                        .message("Create stories has successfully")
                        .success(true)
                        .version(version)
                        .data(newStories)
                        .build());
    }

    @GetMapping("/story/user/{userId}")
    public ResponseEntity<Response> findStoryByUserId(@PathVariable("userId") Long userId) {
        List<Stories> newStories = storyService.findStoryByUserId(userId);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(201)
                        .message("Data Found")
                        .success(true)
                        .version(version)
                        .data(newStories)
                        .build());
    }

}
