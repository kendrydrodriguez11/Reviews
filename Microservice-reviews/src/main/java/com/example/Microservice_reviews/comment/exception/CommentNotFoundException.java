package com.example.Microservice_reviews.comment.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("Comment with ID " + id + " not found");
    }
}