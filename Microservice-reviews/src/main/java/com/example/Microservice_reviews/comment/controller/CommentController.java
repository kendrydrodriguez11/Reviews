package com.example.Microservice_reviews.comment.controller;

import com.example.Microservice_reviews.comment.DTO.required.UpdateCommentDto;
import com.example.Microservice_reviews.comment.DTO.response.SuccessResponse;
import com.example.Microservice_reviews.comment.DTO.response.ErrorResponse;
import com.example.Microservice_reviews.comment.DTO.required.RequestCommentDto;
import com.example.Microservice_reviews.comment.model.CommentEntity;
import com.example.Microservice_reviews.comment.service.CommentService;
import com.example.Microservice_reviews.movie.model.EntityMovie;
import com.example.Microservice_reviews.movie.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class CommentController {
    private final CommentService commentService;
    private final MovieService movieService;

    @GetMapping("/ShowComments/{idMovie}")
    public ResponseEntity<?> getAllComments(@PathVariable Long idMovie) {
        try{
            List<CommentEntity> comments = commentService.getAllComments(idMovie);
            return ResponseEntity.ok(new SuccessResponse<>(200, "Comment added successfully", comments));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Error show comment", e.getMessage()));
        }

    }


    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody @Valid RequestCommentDto comment) {
        try {
            EntityMovie movie = movieService.findMoviesByBY(comment.getMovieId());

            CommentEntity commentSet = CommentEntity.builder()
                    .content(comment.getContent())
                    .rating(comment.getRating())
                    .userId(comment.getUserId())
                    .createdAt(LocalDateTime.now())
                    .movie(movie)
                    .build();

            commentService.addComment(commentSet);
            return ResponseEntity.ok(new SuccessResponse<>(200, "Comment added successfully", null));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "We couldn't find the movie you're looking for", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Error saving comment", null));
        }
    }


    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok(new SuccessResponse<>(200, "Comment deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Error deleting comment", e.getMessage()));
        }
    }

    @PutMapping("/updateComment")
    public ResponseEntity<?> updateComment(@RequestBody @Valid UpdateCommentDto comment) {
        try {
            commentService.UpdateComment(comment);
            return ResponseEntity.ok(new SuccessResponse<>(200, "Comment updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Error updating comment", e.getMessage()));
        }
    }
}
