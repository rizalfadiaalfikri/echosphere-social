package com.rizalfadiaalfikri.echosphere.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rizalfadiaalfikri.echosphere.models.entity.Comments;
import com.rizalfadiaalfikri.echosphere.models.entity.Posts;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.repository.CommentRepository;
import com.rizalfadiaalfikri.echosphere.repository.PostRepository;
import com.rizalfadiaalfikri.echosphere.repository.UserRepository;
import com.rizalfadiaalfikri.echosphere.services.CommentService;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.RowNotFoundDetailNullException;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comments createComment(Comments comments, Long postId, Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundDetailNullException("users with id " + userId + " is not found"));

        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new RowNotFoundDetailNullException("post with id " + postId + " is not found"));

        Comments newComment = new Comments();
        newComment.setUsers(users);
        newComment.setContent(comments.getContent());

        posts.getComments().add(newComment);
        postRepository.save(posts);

        return commentRepository.save(newComment);

    }

    @Override
    public Comments findCommentById(Long commentId) {
        Comments comments = commentRepository.findById(commentId).orElseThrow(
                () -> new RowNotFoundDetailNullException("comment with id " + commentId + " is not found"));

        return comments;
    }

    @Override
    public Comments likeComment(Long commentId, Long userId) {
        Comments comments = commentRepository.findById(commentId).orElseThrow(
                () -> new RowNotFoundDetailNullException("comment with id " + commentId + " is not found"));

        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundDetailNullException("users with id " + userId + " is not found"));

        if (!comments.getLiked().contains(users)) {
            comments.getLiked().add(users);
        } else {
            comments.getLiked().remove(users);
        }

        return commentRepository.save(comments);
    }

}
