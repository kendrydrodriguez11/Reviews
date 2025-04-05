package com.example.Microservice_reviews.movie.DTO.required;

import com.example.Microservice_reviews.movie.model.GenreU;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder
@Data
public class MovieWithGenresDTO {
    private String movieName;
    private Collection<GenreU> genreNames;

    public MovieWithGenresDTO(String movieName, Collection<GenreU> genreNames) {
        this.movieName = movieName;
        this.genreNames = genreNames;
    }
}
