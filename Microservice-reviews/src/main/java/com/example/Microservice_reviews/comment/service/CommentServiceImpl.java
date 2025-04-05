package com.example.Microservice_reviews.comment.service;

import com.example.Microservice_reviews.comment.DTO.required.UpdateCommentDto;
import com.example.Microservice_reviews.comment.exception.CommentNotFoundException;
import com.example.Microservice_reviews.comment.model.CommentEntity;
import com.example.Microservice_reviews.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements  CommentService{
    private final CommentRepository commentRepository;

    @Override
    public void addComment(CommentEntity comment) {
        commentRepository.addComment(comment);
    }

    @Override
    public void deleteComment(Long idComment) {
        commentRepository.deleteComment(idComment);
    }


    @Override
    public void UpdateComment(UpdateCommentDto commentInf) {
        CommentEntity comment = commentRepository.FindCommentById(commentInf.getId());
        if (comment == null) {
            throw new CommentNotFoundException(commentInf.getId());
        }
        comment.setContent(commentInf.getContent());
        commentRepository.UpdateComment(comment);
    }

    @Override
    public List<CommentEntity> getAllComments(Long idMovie) {
        return commentRepository.showAllComments(idMovie);
    }
}
