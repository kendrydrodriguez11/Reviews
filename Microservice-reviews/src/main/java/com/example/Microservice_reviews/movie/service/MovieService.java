package com.example.Microservice_reviews.movie.service;

import com.example.Microservice_reviews.movie.DTO.required.MovieWithGenresDTO;
import com.example.Microservice_reviews.movie.model.EntityMovie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    EntityMovie findMoviesByBY(Long id);

    List<EntityMovie> findMoviesByName(String name);

    List<EntityMovie> findMoviesByGenre(String genre);

    List<EntityMovie> getMovies();

    void saveMovie(EntityMovie movie);

    void updateMovie(EntityMovie movie);

    void deleteMovie(Long idMovie);

}
