package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.dto.actor_Dto;
import org.project2.tz1_cinema.dto.movie_Dto;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepo movieRepo;
    private final ActorRepo actorRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo, ActorRepo actorRepo) {
        this.movieRepo = movieRepo;
        this.actorRepo = actorRepo;
    }
    public Movie getMovieByTitle(String title) {
        return movieRepo.findByTitle(title).orElse(null);
    }

    // Метод для получения всех фильмов
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    // Метод для получения фильма по ID
    public Movie getMovieById(int id) {
        return movieRepo.findById(id).orElse(null);  // Лучше использовать orElse(null), чтобы избежать исключений
    }

    // Метод для добавления фильма
    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> getByReleaseYear(int year) {
        return movieRepo.getMovieByReleaseYear(year);
    }

    // Метод для обновления фильма
    public Movie updateMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    // Метод для удаления фильма
    public void deleteMovie(int id) {
        movieRepo.deleteById(id);
    }
    public List<Movie> releaseDate(int releaseData){
        return movieRepo.findByRelease_data(releaseData);
    }
    // Метод для получения фильмов по актёру
    public List<Movie> findByActor(Actor actor) {
        return movieRepo.findByActors(actor);  // Возвращаем список фильмов по актёру
    }
    public Movie addMovie(movie_Dto movieDto) {
        // Создаем новый объект фильма из DTO
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setCountry(movieDto.getCountry());
        movie.setGenre(movieDto.getGenre());

        // Список для хранения актеров, которые будут привязаны к фильму
        List<Actor> actors = new ArrayList<>();

        for (actor_Dto actor : movieDto.getActors()) {
            Optional<Actor> existingActorOpt = Optional.ofNullable(actorRepo.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName()));

            // Проверяем, если актер уже есть в базе
            Actor actorToAdd;
            if (existingActorOpt.isPresent()) {
                actorToAdd = existingActorOpt.get();
            } else {
                // Если актера нет в базе, добавляем его

                actorToAdd = actorRepo.save(convertActorDtoToActor(actor));  // Сохраняем нового актера в базе
            }
            actors.add(actorToAdd);
        }

        // Устанавливаем актеров для фильма
        movie.setActors(actors);

        // Сохраняем фильм
        return movieRepo.save(movie);
    }
    public void save(Movie movie){
        movieRepo.save(movie);
    }
    public Actor convertActorDtoToActor(actor_Dto actorDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setYearOfBirth(actorDto.getYearOfBirth());
        return actor;
    }
    public void updateComment(Comment comment) {

    }
}