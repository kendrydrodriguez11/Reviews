package com.example.Microservice_reviews.movie.DTO.required;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Data
public class SaveMovie {
    @NotBlank
    private String name;

    @NotNull
    private LocalDate releasedate;

    @NotNull @NotNull
    private Long idCrator;

    @NotNull
    private List<String> genre;
}
