package org.project2.tz1_cinema.service;

import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.dto.actor_Dto;
import org.project2.tz1_cinema.dto.movie_Dto;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieService {
    private final MovieRepo movieRepo;
    private final ActorRepo actorRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo, ActorRepo actorRepo) {
        this.movieRepo = movieRepo;
        this.actorRepo = actorRepo;
    }
    public Movie getMovieByTitle(String title) {
        log.info("getMovieByTitle: {}", title);
        return movieRepo.findByTitle(title).orElse(null);
    }

    public List<Movie> getAllMovies() {
        log.info("getAllMovies");
        return movieRepo.findAll();
    }


    public Movie getMovieById(int id) {
        log.info("getMovieById: {}", id);
        return movieRepo.findById(id).orElse(null);
    }

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> getByReleaseYear(int year) {
        log.info("getByReleaseYear: {}", year);
        return movieRepo.getMovieByReleaseYear(year);
    }

    public Movie updateMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public void deleteMovie(int id) {
        movieRepo.deleteById(id);
    }
    public List<Movie> releaseDate(int releaseData){
        return movieRepo.findByRelease_data(releaseData);
    }
    public List<Movie> findByActor(Actor actor) {
        return movieRepo.findByActors(actor);
    }
    public Movie addMovie(movie_Dto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setCountry(movieDto.getCountry());
        movie.setGenre(movieDto.getGenre());

        List<Actor> actors = new ArrayList<>();

        for (actor_Dto actor : movieDto.getActors()) {
            Optional<Actor> existingActorOpt = Optional.ofNullable(actorRepo.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName()));

            Actor actorToAdd;
            actorToAdd = existingActorOpt.orElseGet(() -> actorRepo.save(convertActorDtoToActor(actor)));
            actors.add(actorToAdd);
        }

        movie.setActors(actors);

        return movieRepo.save(movie);
    }
    @Transactional
    public void save(Movie movie){
        log.info("save: {}", movie);
        movieRepo.save(movie);
    }
    public Actor convertActorDtoToActor(actor_Dto actorDto) {
        log.info("convertActorDtoToActor: {}", actorDto);
        Actor actor = new Actor();
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setYearOfBirth(actorDto.getYearOfBirth());
        return actor;
    }
    public void updateComment(Comment comment) {

    }
}