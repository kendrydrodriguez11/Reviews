package com.example.Microservice_reviews.movie.controllers;

import com.example.Microservice_reviews.movie.DTO.required.MovieWithGenresDTO;
import com.example.Microservice_reviews.movie.DTO.required.SaveMovie;
import com.example.Microservice_reviews.movie.model.EntityGenre;
import com.example.Microservice_reviews.movie.model.EntityMovie;
import com.example.Microservice_reviews.movie.model.GenreU;
import com.example.Microservice_reviews.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MoviesControllers {
    private final MovieService movieService;

    @GetMapping("/get/{idMovie}")
    public ResponseEntity<?> getMovie(@PathVariable Long idMovie) {
        try{
            EntityMovie movies = movieService.findMoviesByBY(idMovie);
            return ResponseEntity.ok(movies);
        }catch (Exception e ){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getMoviesByName(@PathVariable String name) {
        try{

            List<EntityMovie> movies = movieService.findMoviesByName(name);
            return ResponseEntity.ok(movies);
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: "+ e.getMessage());
        }
    }


    @GetMapping("/getByGenre/{genre}")
    public ResponseEntity<?> getMoviesByGenre(@PathVariable String genre) {
        try {

            List<EntityMovie> movies = movieService.findMoviesByGenre(genre);

            if (!movies.isEmpty()) {
                return ResponseEntity.ok(movies);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No movies found for genre: " + genre);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid genre: " + genre + ". Valid genres are: " + Arrays.toString(GenreU.values()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<EntityMovie>> getAllMovies() {
        List<EntityMovie> movies = movieService.getMovies();
        return ResponseEntity.ok(movies);
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveMovie(@RequestBody SaveMovie movieRequest) {
        Set<EntityGenre> genreSet = movieRequest.getGenre().stream()
                .map(value -> EntityGenre.builder()
                        .name(GenreU.valueOf(value))
                        .build())
                .collect(Collectors.toSet());

        EntityMovie movie = EntityMovie.builder()
                .name(movieRequest.getName())
                .releasedate(movieRequest.getReleasedate())
                .idCrator(movieRequest.getIdCrator())
                .genre(genreSet)
                .build();

        movieService.saveMovie(movie);
        return ResponseEntity.ok("Movie saved successfully");
    }



    @PutMapping("/update")
    public ResponseEntity<String> updateMovie(@RequestBody EntityMovie movie) {
        movieService.updateMovie(movie);
        return ResponseEntity.ok("Movie updated successfully");
    }

    @DeleteMapping("/delete/{idMovie}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long idMovie) {
        movieService.deleteMovie(idMovie);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}
