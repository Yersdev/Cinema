package org.project2.tz1_cinema.controller;

import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.service.ActorDirectorService;
import org.project2.tz1_cinema.service.ActorService;
import org.project2.tz1_cinema.service.DirectorService;
import org.project2.tz1_cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final MovieService movieService;
    private final DirectorService directorService;
    private final ActorService actorService;
    private final ActorRepo actorRepo;
    private final ActorDirectorService actorDirectorService;

    @Autowired
    public AdminController(MovieService movieService, DirectorService directorService, ActorService actorService, ActorRepo actorRepo, ActorDirectorService actorDirectorService) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.actorService = actorService;
        this.actorRepo = actorRepo;
        this.actorDirectorService = actorDirectorService;
    }

    @PostMapping(value = "/movies/add", consumes = "application/json")
    public ResponseEntity<Movie> addMovie(@RequestBody movie_Dto movieDto) {
        Movie movie1 = new Movie();
        movie1.setTitle(movieDto.getTitle());
        movie1.setCountry(movieDto.getCountry());
        movie1.setGenre(movieDto.getGenre());
        movie1.setActors(ActorDtoListconvertActorList(movieDto.getActors()));
        List<Actor> actors = ActorDtoListconvertActorList(movieDto.getActors());
        actorRepo.saveAll(actors);
        movie1.setReleaseYear(movieDto.getReleaseYear());
        movie1.setComments(movie1.getComments());
        movie1.setDirector(convactorDtoDirector(movieDto.getDirector()));

        movieService.save(movie1);

        return ResponseEntity.status(HttpStatus.CREATED).body(movie1);
    }
/*
    {
        "title": "The Matrix",
            "country": "USA",
            "genre": "Sci-Fi",
            "releaseYear": 1999,
            "actors": [
        { "firstName": "Keanu", "lastName": "Reeves", "yearOfBirth": 1964 },
        { "firstName": "Laurence", "lastName": "Fishburne", "yearOfBirth": 1961 },
        { "firstName": "Carrie-Anne", "lastName": "Moss", "yearOfBirth": 1967 }
  ],
        "director": {
        "firstName": "Lana",
                "lastName": "Wachowski",
                "yearOfBirth": 1965
    }
    }
*/


    @PostMapping("/actor/add")
    public ResponseEntity<actor_Dto> addActor(@RequestBody actor_Dto actorDto) {
        if (actorDto.getFirstName() == null || actorDto.getFirstName().isEmpty() || actorDto.getLastName() == null || actorDto.getLastName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        actorService.save(convertToActorDto(actorDto));

        return new ResponseEntity<>(actorDto, HttpStatus.CREATED);
    }




    @PostMapping("/director/add")
    public ResponseEntity<director_Dto> addDirector(@RequestBody director_Dto directorDto) {
        Director director = new Director();
        director.setFirstName(directorDto.getFirstName());
        director.setLastName(directorDto.getLastName());
        director.setYearOfBirth(directorDto.getYearOfBirth());
        directorService.save(director);
        return new ResponseEntity<>(directorDto, HttpStatus.CREATED);
    }
    //{
    //  "firstName": "Martin",
    //  "lastName": "Scorsese",
    //  "yearOfBirth": "1942"
    //}

    private Actor convertToActorDto(actor_Dto actorAddDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorAddDto.getFirstName());
        actor.setLastName(actorAddDto.getLastName());
        actor.setYearOfBirth(actorAddDto.getYearOfBirth());
        return actor;
    }

    private Director convactorDtoDirector(director_Dto DirectorAddDto) {
        Director director = new Director();
        director.setFirstName(DirectorAddDto.getFirstName());
        director.setLastName(DirectorAddDto.getLastName());
        director.setYearOfBirth(DirectorAddDto.getYearOfBirth());
        return director;
    }

    private List<Actor> ActorDtoListconvertActorList(List<actor_Dto> actorAddDtoList) {
        List<Actor> actors = new ArrayList<>();
        for (actor_Dto actorAddDto : actorAddDtoList) {
            Actor actor = convertToActorDto(actorAddDto);
            actor.setFirstName(actorAddDto.getFirstName());
            actor.setLastName(actorAddDto.getLastName());
            actor.setYearOfBirth(actorAddDto.getYearOfBirth());
            actors.add(actor);
        }
        return actors;
    }
}
