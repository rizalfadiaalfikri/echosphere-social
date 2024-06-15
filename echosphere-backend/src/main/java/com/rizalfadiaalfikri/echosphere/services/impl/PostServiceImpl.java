package com.rizalfadiaalfikri.echosphere.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rizalfadiaalfikri.echosphere.models.entity.Posts;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.req.PostsDto;
import com.rizalfadiaalfikri.echosphere.repository.PostRepository;
import com.rizalfadiaalfikri.echosphere.repository.UserRepository;
import com.rizalfadiaalfikri.echosphere.services.PostService;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.RowNotFoundDetailNullException;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Posts createNewPost(PostsDto dto, Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundDetailNullException("users with id " + userId + " is not found"));

        Posts newPost = new Posts();
        newPost.setCaption(dto.getCaption());
        newPost.setImage(dto.getImage());
        newPost.setVideo(dto.getVideo());
        newPost.setUsers(users);

        return postRepository.save(newPost);

    }

    @Override
    public void deletePost(Long postId, Long userId) {
        userRepository.findById(postId).orElseThrow(
                () -> new RowNotFoundDetailNullException("users with id " + userId + " is not found"));
        Posts post = postRepository.findById(postId).orElseThrow(
                () -> new RowNotFoundDetailNullException("post with id " + postId + " is not found"));

        postRepository.delete(post);
    }

    @Override
    public List<Posts> findPostsByUserId(Long userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public List<Posts> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Posts savedPost(Long postId, Long userId) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new RowNotFoundDetailNullException("posts with id " + postId + " is not found"));

        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundDetailNullException("users with id " + userId + " is not found"));

        if (users.getSavedPost().contains(posts)) {
            users.getSavedPost().remove(posts);
        } else {
            users.getSavedPost().add(posts);
        }
        userRepository.save(users);
        return posts;
    }

    @Override
    public Posts likePost(Long postId, Long userId) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new RowNotFoundDetailNullException("posts with id " + postId + " is not found"));

        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundDetailNullException("users with id " + userId + " is not found"));

        if (posts.getLiked().contains(users)) {
            posts.getLiked().remove(users);
        } else {
            posts.getLiked().add(users);
        }

        return postRepository.save(posts);

    }

    @Override
    public Posts findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new RowNotFoundDetailNullException("post with id " + postId + " is not found"));
    }

}
