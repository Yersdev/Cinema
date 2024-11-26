package org.project2.tz1_cinema.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.*;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.project2.tz1_cinema.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepo movieRepo;
    private final MovieService movieService;
    private final ActorRepo actorRepo;

    @GetMapping
    public ResponseEntity<List<movie_Dto>> getAllMovies() {
        List<movie_Dto> movies = movieConvertToDto(movieRepo.findAll());
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{year}")
    public ResponseEntity<List<movie_Dto>> getMoviesByYear(@PathVariable int year) {
        List<Movie> movies = movieService.getByReleaseYear(year);
        List<movie_Dto> movieDtos = movieConvertToDto(movies);

        if (movieDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    @GetMapping("/{movieId}/actors")
    public ResponseEntity<List<actor_Dto>> getActorsByMovie(@PathVariable int movieId) {
        Movie movie = movieRepo.findById(movieId).orElse(null);

        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Actor> actors = movie.getActors();
        List<actor_Dto> actorDtos = actorConvertToDtoList(actors);

        if (actorDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(actorDtos, HttpStatus.OK);
    }

    @GetMapping("/{actorId}/all_movies")
    public ResponseEntity<List<movie_Dto>> getAllMoviesByActor(@PathVariable int actorId) {
        Actor actor = actorRepo.findById(actorId);

        if (actor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Movie> movies = movieRepo.findByActors(actor);
        List<movie_Dto> movieDtos = movieConvertToDto(movies);

        return ResponseEntity.ok(movieDtos);
    }

    private List<movie_Dto> movieConvertToDto(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return Collections.emptyList();
        }
        List<movie_Dto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            movie_Dto movieDto = new movie_Dto();
            movieDto.setTitle(movie.getTitle());
            List<actor_Dto> actorDtos = new ArrayList<>();
            for (Actor actor : movie.getActors()) {
                actorDtos.add(actorConvertToDto(actor));
            }
            movieDto.setActors(actorDtos);
            movieDto.setDirector(directorDtoConvertToDto(movie.getDirector()));
            movieDto.setReleaseYear(movie.getReleaseYear());
            movieDto.setCountry(movie.getCountry());
            movieDto.setGenre(movie.getGenre());
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }

    private director_Dto directorDtoConvertToDto(Director director) {
        if (director == null) {
            return null;
        }
        director_Dto directorDto = new director_Dto();
        directorDto.setFirstName(director.getFirstName());
        directorDto.setLastName(director.getLastName());
        directorDto.setYearOfBirth(director.getYearOfBirth());
        return directorDto;
    }

    private actor_Dto actorConvertToDto(Actor actor) {
        if (actor == null) {
            return null;
        }
        actor_Dto actorDto = new actor_Dto();
        actorDto.setFirstName(actor.getFirstName());
        actorDto.setLastName(actor.getLastName());
        actorDto.setYearOfBirth(actor.getYearOfBirth());
        return actorDto;
    }

    private List<actor_Dto> actorConvertToDtoList(List<Actor> actors) {
        if (actors == null || actors.isEmpty()) {
            return Collections.emptyList();
        }
        List<actor_Dto> actorDtos = new ArrayList<>();
        for (Actor actor : actors) {
            actorDtos.add(actorConvertToDto(actor));
        }
        return actorDtos;
    }
}
