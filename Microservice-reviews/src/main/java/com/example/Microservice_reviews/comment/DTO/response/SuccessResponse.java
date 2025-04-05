package com.example.Microservice_reviews.comment.DTO.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse<T> {
    private int status;
    private String message;
    private T data;
}
