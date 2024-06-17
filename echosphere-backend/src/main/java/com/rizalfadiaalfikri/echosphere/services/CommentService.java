package com.rizalfadiaalfikri.echosphere.services;

import com.rizalfadiaalfikri.echosphere.models.entity.Comments;

public interface CommentService {
    public Comments createComment(Comments comments, Long postId, Long userId);

    public Comments findCommentById(Long commentId);

    public Comments likeComment(Long commentId, Long userId);
}
