package com.rizalfadiaalfikri.echosphere.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rizalfadiaalfikri.echosphere.models.entity.Comments;
import com.rizalfadiaalfikri.echosphere.models.req.CommentsDto;
import com.rizalfadiaalfikri.echosphere.models.res.Response;
import com.rizalfadiaalfikri.echosphere.services.CommentService;

@RequestMapping("/api/v1")
@RestController
public class CommentController {

    @Value("${application.version}")
    private String version;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/post/{postId}/user/{userId}")
    public ResponseEntity<Response> createComment(
            @RequestBody Comments comments,
            @PathVariable("postId") Long postId,
            @PathVariable("userId") Long userId) {
        Comments newComments = commentService.createComment(comments, postId, userId);

        return ResponseEntity.created(URI.create("")).body(
                Response.builder()
                        .code(201)
                        .message("Create comments has successfully")
                        .success(true)
                        .version(version)
                        .data(newComments)
                        .build());
    }

    @PutMapping("/comments/like/{commentId}/user/{userId}")
    public ResponseEntity<Response> likeComment(
            @PathVariable("commentId") Long commentId,
            @PathVariable("userId") Long userId) {
        Comments newComments = commentService.likeComment(commentId, userId);

        return ResponseEntity.ok().body(
                Response.builder()
                        .code(200)
                        .message("Like comments has successfully")
                        .success(true)
                        .version(version)
                        .data(newComments)
                        .build());
    }

}
