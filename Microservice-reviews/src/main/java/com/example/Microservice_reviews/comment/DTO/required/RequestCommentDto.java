package com.example.Microservice_reviews.comment.DTO.required;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestCommentDto {
    @Size(max = 200, message = "A message with more than 200 characters is not permitted")
    @NotNull
    private String content;

    @Min(1) @Max(5)
    @NotNull
    private Integer rating;

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;


}
