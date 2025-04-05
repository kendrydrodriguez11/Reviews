package com.example.Microservice_reviews.movie.repository;

import com.example.Microservice_reviews.movie.DTO.required.MovieWithGenresDTO;
import com.example.Microservice_reviews.movie.model.EntityGenre;
import com.example.Microservice_reviews.movie.model.EntityMovie;
import com.example.Microservice_reviews.movie.model.GenreU;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MovieRepositoryImpl implements MovieRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public EntityMovie findMovieWithGenresById(Long id) {
        String query = "SELECT m FROM EntityMovie m WHERE m.id = :id";

        EntityMovie movie = entityManager.createQuery(query, EntityMovie.class)
                .setParameter("id", id)
                .getSingleResult();
        return movie;
    }


    @Override
    public List<EntityMovie> findMoviesByName(String name) {
        String query = "select u from EntityMovie u where u.name = :name";
        List<EntityMovie> movies = entityManager.createQuery(query, EntityMovie.class)
                .setParameter("name", name)
                .getResultList();
        return movies;
    }

    @Override
    public List<EntityMovie> findMoviesByGenre(GenreU genre) {
        String query = "select u from EntityMovie u Join u.genre g where g.name = :name";
        List<EntityMovie> movie = entityManager.createQuery(query, EntityMovie.class)
                .setParameter("name", genre)
                .getResultList();
        return movie;
    }


    @Override
    public List<EntityMovie> getMovies() {
        String query = "FROM EntityMovie";
        List<EntityMovie> movies = entityManager.createQuery(query, EntityMovie.class)
                .getResultList();
        return movies;
    }

    @Override
    public void saveMovie(EntityMovie movie) {
        entityManager.persist(movie);
    }

    @Override
    public void updateMovie(EntityMovie movie) {
        entityManager.merge(movie);
    }

    @Override
    public void deleteMovie(Long idMovie) {
        EntityMovie managedMovie = entityManager.find(EntityMovie.class, idMovie);
        if (managedMovie != null) {
            entityManager.remove(managedMovie);
        }
    }
}
