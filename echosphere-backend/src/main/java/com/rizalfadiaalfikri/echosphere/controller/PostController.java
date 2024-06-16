package com.rizalfadiaalfikri.echosphere.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rizalfadiaalfikri.echosphere.models.entity.Posts;
import com.rizalfadiaalfikri.echosphere.models.req.PostsDto;
import com.rizalfadiaalfikri.echosphere.models.res.Response;
import com.rizalfadiaalfikri.echosphere.services.PostService;

@RequestMapping("/api/v1")
@RestController
public class PostController {

        @Value("${application.version}")
        private String version;

        @Autowired
        private PostService postService;

        @PostMapping("/posts")
        public ResponseEntity<Response> createNewPost(@RequestBody PostsDto dto) {
                Posts posts = postService.createNewPost(dto, dto.getUsersId());

                return ResponseEntity.created(URI.create("")).body(
                                Response.builder()
                                                .code(201)
                                                .message("Create post has successfully")
                                                .success(true)
                                                .version(version)
                                                .data(posts)
                                                .build());
        }

        @DeleteMapping("/posts/{postId}/user/{userId}")
        public ResponseEntity<Response> deletePost(@PathVariable("postId") Long postId,
                        @PathVariable("userId") Long userId) {
                postService.deletePost(postId, userId);

                return ResponseEntity.ok().body(
                                Response.builder()
                                                .code(200)
                                                .message("Delete post has successfully")
                                                .success(true)
                                                .version(version)
                                                .build());
        }

        @GetMapping("/posts/users/{userId}")
        public ResponseEntity<Response> findPostByUserId(@PathVariable("userId") Long userId) {
                List<Posts> posts = postService.findPostsByUserId(userId);

                return ResponseEntity.ok().body(
                                Response.builder()
                                                .code(200)
                                                .message("Data Found")
                                                .success(true)
                                                .version(version)
                                                .data(posts)
                                                .build());
        }

        @GetMapping("/posts/{postId}")
        public ResponseEntity<Response> findPostById(@PathVariable("postId") Long postId) {
                Posts posts = postService.findPostById(postId);

                return ResponseEntity.ok().body(
                                Response.builder()
                                                .code(200)
                                                .message("Data Found")
                                                .success(true)
                                                .version(version)
                                                .data(posts)
                                                .build());
        }

        @GetMapping("/posts")
        public ResponseEntity<Response> findAllPost() {
                List<Posts> posts = postService.findAllPosts();

                return ResponseEntity.ok().body(
                                Response.builder()
                                                .code(200)
                                                .message("Data Found")
                                                .success(true)
                                                .version(version)
                                                .data(posts)
                                                .build());
        }

        @PutMapping("/posts/liked/{postId}/user/{userId}")
        public ResponseEntity<Response> likePost(@PathVariable("postId") Long postId,
                        @PathVariable("userId") Long userId) {
                Posts posts = postService.likePost(postId, userId);

                return ResponseEntity.ok().body(
                                Response.builder()
                                                .code(200)
                                                .message("Like Post Has Successfully")
                                                .success(true)
                                                .version(version)
                                                .data(posts)
                                                .build());
        }

        @PutMapping("/posts/saved/{postId}/user/{userId}")
        public ResponseEntity<Response> savePost(@PathVariable("postId") Long postId,
                        @PathVariable("userId") Long userId) {

                Posts posts = postService.savedPost(postId, userId);

                return ResponseEntity.ok().body(
                                Response.builder()
                                                .code(200)
                                                .message("Save Post Has Successfully")
                                                .success(true)
                                                .version(version)
                                                .data(posts)
                                                .build());
        }
}
