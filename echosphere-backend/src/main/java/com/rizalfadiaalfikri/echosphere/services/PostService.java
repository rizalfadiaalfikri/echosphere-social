package com.rizalfadiaalfikri.echosphere.services;

import java.util.List;

import com.rizalfadiaalfikri.echosphere.models.entity.Posts;
import com.rizalfadiaalfikri.echosphere.models.req.PostsDto;

public interface PostService {
    public Posts createNewPost(PostsDto dto, Long userId);

    public void deletePost(Long postId, Long userId);

    public List<Posts> findPostsByUserId(Long userId);

    public Posts findPostById(Long postId);

    public List<Posts> findAllPosts();

    public Posts savedPost(Long postId, Long userId);

    public Posts likePost(Long postId, Long userId);
}
