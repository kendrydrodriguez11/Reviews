package com.example.Microservice_reviews.comment.service;

import com.example.Microservice_reviews.comment.DTO.required.UpdateCommentDto;
import com.example.Microservice_reviews.comment.model.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getAllComments(Long idMovie);

    void addComment( CommentEntity comment);

    void deleteComment(Long idComment);

    void UpdateComment( UpdateCommentDto comment);
}
