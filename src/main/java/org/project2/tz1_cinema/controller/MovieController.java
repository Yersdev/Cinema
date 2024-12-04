package org.project2.tz1_cinema.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.*;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.project2.tz1_cinema.service.ActorService;
import org.project2.tz1_cinema.service.DirectorService;
import org.project2.tz1_cinema.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieRepo movieRepo;
    private final MovieService movieService;
    private final ActorRepo actorRepo;
    private final UserRepo userRepo;
    private final CommentRepo commentRepo;
    private final ActorService actorService;
    private final DirectorService directorService;

    //all movies
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<movie_Dto>> getAllMovies() {
        List<movie_Dto> movies = movieConvertToDto(movieRepo.findAll());
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{year}")
    public ResponseEntity<List<movie_Dto>> getMoviesByYear(@PathVariable int year) {
        List<Movie> movies = movieService.getByReleaseYear(year);
        List<movie_Dto> movieDtos = movieConvertToDto(movies);

        if (movieDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    //get movie where we have the current actor
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{actorId}/all_movies")
    public ResponseEntity<List<movie_Dto>> getAllMoviesByActor(@PathVariable int actorId) {
        // Получаем актера
        Actor actor = actorRepo.findById(actorId);
        if (actor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Movie> movies = movieRepo.findByActors(actor);
        List<movie_Dto> movieDtos = movieConvertToDto(movies);
        return ResponseEntity.ok(movieDtos);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{movieId}/comments")
    public ResponseEntity<List<comment_Dto>> getCommentsByMovie(@PathVariable int movieId) {
        Movie movie = movieRepo.findById(movieId).orElse(null);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comment> comments = movie.getComments();
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<comment_Dto> commentDtos = commentConvertToDto(comments);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{idCinema}/addComment", consumes = "application/json")
    public ResponseEntity<Comment> addMovie(@RequestBody comment_details_dto dto,
                                            @PathVariable Integer idCinema) {
        Movie movie = movieRepo.findById(idCinema).orElse(null);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!userRepo.existsByName(dto.getFirstName())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Movie movie1 = movieRepo.findById(idCinema)
                .orElseThrow(() -> new EntityNotFoundException("Movie with ID " + idCinema + " not found"));
        Users user = userRepo.findById(userRepo.findUsersByEmail(dto.getEmail()).getId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userRepo.findUsersByEmail(dto.getEmail()).getId() + " not found"));
        Comment newComment = new Comment();
        newComment.setComment(dto.getComments());
        newComment.setMovie(movie);
        newComment.setUsers(user);
        commentRepo.save(newComment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }
//    ------------------ADMIN----------------------

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/actor/add")
    public ResponseEntity<actor_Dto> addActor(@RequestBody actor_Dto actorDto) {
        if (actorDto.getFirstName() == null || actorDto.getFirstName().isEmpty() || actorDto.getLastName() == null || actorDto.getLastName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        actorService.save(convertToActorDto(actorDto));

        return new ResponseEntity<>(actorDto, HttpStatus.CREATED);
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/director/add")
    public ResponseEntity<director_Dto> addDirector(@RequestBody director_Dto directorDto) {
        Director director = new Director();
        director.setFirstName(directorDto.getFirstName());
        director.setLastName(directorDto.getLastName());
        director.setYearOfBirth(directorDto.getYearOfBirth());
        directorService.save(director);
        return new ResponseEntity<>(directorDto, HttpStatus.CREATED);
    }

//----------------------CONVERTER--------------------------------
    public UserDto userConvertToDto(Users users) {
        if (users == null) {
            return null;}
        UserDto userDto = new UserDto();
        userDto.setName(users.getName());
        userDto.setLast_name(users.getLast_name());
        userDto.setEmail(users.getEmail());
        return userDto;
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
    public List<comment_Dto> commentConvertToDto(List<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            return Collections.emptyList();
        }
        List<comment_Dto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            comment_Dto commentDto = new comment_Dto();
            commentDto.setComment(comment.getComment());
            commentDto.setUserdto(userConvertToDto(comment.getUsers()));
            commentDto.setMovie(comment.getMovie()); commentDtos.add(commentDto);
        }
        return commentDtos;
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
}
