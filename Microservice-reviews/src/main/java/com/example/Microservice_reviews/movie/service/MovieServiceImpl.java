package com.example.Microservice_reviews.movie.service;

import com.example.Microservice_reviews.movie.DTO.required.MovieWithGenresDTO;
import com.example.Microservice_reviews.movie.model.EntityMovie;
import com.example.Microservice_reviews.movie.model.GenreU;
import com.example.Microservice_reviews.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements  MovieService{
    private final MovieRepository movieRepository;


    @Override
    public EntityMovie findMoviesByBY(Long id) {
        EntityMovie movie = movieRepository.findMovieWithGenresById(id);
        return movie;
    }


    @Override
    public List<EntityMovie> findMoviesByName(String name) {
        List<EntityMovie> movies = movieRepository.findMoviesByName(name);
        return movies != null ? movies : Collections.emptyList();
    }

    @Override
    public List<EntityMovie> findMoviesByGenre(String genre) {
        GenreU nameGenre = GenreU.valueOf(genre.toUpperCase());
        List<EntityMovie> movies = movieRepository.findMoviesByGenre(nameGenre);
        return !movies.isEmpty() ? movies : Collections.emptyList();
    }

    @Override
    public List<EntityMovie> getMovies() {
        return movieRepository.getMovies();
    }

    @Override
    public void saveMovie(EntityMovie movie) {
        movieRepository.saveMovie(movie);
    }

    @Override
    public void updateMovie(EntityMovie movie) {
        movieRepository.updateMovie(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteMovie(id);
    }
}
