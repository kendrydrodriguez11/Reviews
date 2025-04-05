package com.example.Microservice_reviews.comment.DTO.required;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateCommentDto {

    @NotNull(message = "Comment ID is required")
    private Long id;

    @Size(max = 200, message = "A message with more than 200 characters is not permitted")
    @NotNull(message = "Content cannot be null")
    private String content;

}
