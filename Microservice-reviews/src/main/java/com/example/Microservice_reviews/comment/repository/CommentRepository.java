package com.example.Microservice_reviews.comment.repository;

import com.example.Microservice_reviews.comment.model.CommentEntity;

import java.util.List;

public interface CommentRepository {
    void addComment( CommentEntity comment);

    void deleteComment(Long idComment);

    List<CommentEntity> showAllComments(Long idMovie);

    void UpdateComment( CommentEntity comment);

    CommentEntity FindCommentById(Long id);
}
