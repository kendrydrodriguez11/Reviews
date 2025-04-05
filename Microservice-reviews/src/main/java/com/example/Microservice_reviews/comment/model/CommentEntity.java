package com.example.Microservice_reviews.comment.model;

import com.example.Microservice_reviews.movie.model.EntityMovie;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name ="commentEntity")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200, message = "A message with more than 200 characters is not permitted")
    @NotNull
    private String content;

    @Min(1) @Max(5)
    @NotNull
    private Integer rating;

    @NotNull
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private EntityMovie movie;

    @NotNull
    private LocalDateTime createdAt;
}
