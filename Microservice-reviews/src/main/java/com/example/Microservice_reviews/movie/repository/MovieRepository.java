package com.example.Microservice_reviews.movie.repository;

import com.example.Microservice_reviews.movie.DTO.required.MovieWithGenresDTO;
import com.example.Microservice_reviews.movie.model.EntityMovie;
import com.example.Microservice_reviews.movie.model.GenreU;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository {

    EntityMovie findMovieWithGenresById(Long id);

    List<EntityMovie> findMoviesByName(String name);

    List<EntityMovie> findMoviesByGenre(GenreU genre);

    List<EntityMovie> getMovies();

    void saveMovie(EntityMovie movie);

    void updateMovie(EntityMovie movie);

    void deleteMovie(Long idMovie);



}
