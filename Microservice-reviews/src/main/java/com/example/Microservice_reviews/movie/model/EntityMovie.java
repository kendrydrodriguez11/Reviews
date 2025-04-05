package com.example.Microservice_reviews.movie.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EntityMovie")
public class EntityMovie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate releasedate;

    @NotNull @NotNull
    private Long idCrator;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = EntityGenre.class, cascade = CascadeType.PERSIST)
    @JoinTable (name = "genreMovie", joinColumns = @JoinColumn(name = "idMovie"), inverseJoinColumns = @JoinColumn(name = "idGenre"))
    private Set<EntityGenre> genre;

}
