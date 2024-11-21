package org.project2.tz1_cinema.controller;

import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.*;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.project2.tz1_cinema.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final MovieRepo movieRepo;
    private final MovieService movieService;
    private final ActorRepo actorRepo;
    private final UserRepo userRepo;

    public UserController(MovieRepo movieRepo, MovieService movieService, ActorRepo actorRepo, UserRepo userRepo) {
        this.movieRepo = movieRepo;
        this.movieService = movieService;
        this.actorRepo = actorRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<movie_Dto>> getAllMovies() {
        List<movie_Dto> movies = movieConvertToDto(movieRepo.findAll());
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movies/{year}")
    public ResponseEntity<List<movie_Dto>> getMoviesByYear(@PathVariable int year) {
        List<Movie> movies = movieService.getByReleaseYear(year);
        List<movie_Dto> movieDtos = movieConvertToDto(movies);

        if (movieDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    @GetMapping("/movies/{movieId}/actors")
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

    @GetMapping("/movies/{movieId}/comments")
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
    @GetMapping("/movies/{actorId}/all_movies")
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

    @GetMapping("/{id}")
    public ResponseEntity<comment_details_dto> getUserComments(@PathVariable int id) {
        Users user = userRepo.findById(id).orElse(null); // Используем Optional для удобства
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Возвращаем 404, если пользователь не найден
        }

        comment_details_dto userCommentsDto = new comment_details_dto(user.getName(), user.getEmail());

        List<Comment> comments = user.getComments();

        for (Comment comment : comments) {
            String movieTitle = comment.getMovie().getTitle();  // Название фильма
            String userComment = comment.getComment(); // Текст комментария
            userCommentsDto.addComment(movieTitle, userComment);
        }

        return new ResponseEntity<>(userCommentsDto, HttpStatus.OK);
    }


//
//        @PostMapping("/add")
//        public ResponseEntity<String> addReview(@RequestBody ReviewDto reviewDto, @RequestHeader("Authorization") String token) {
//            // Проверка на наличие токена и авторизацию пользователя
//            if (token == null || !userService.isValidToken(token)) {
//                return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
//            }
//
//            // Валидация данных отзыва
//            if (reviewDto.getRating() < 1 || reviewDto.getRating() > 5) {
//                return new ResponseEntity<>("Rating must be between 1 and 5", HttpStatus.BAD_REQUEST);
//            }
//
//            // Добавление отзыва
//            reviewService.addReview(reviewDto);
//            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
//        }
//    }
//


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
                actorDtos.add(actorConvertToDto(actor));// Один список актеров для каждого фильма
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

    public UserDto userConvertToDto(Users users) {
        if (users == null) {
            return null;}
        UserDto userDto = new UserDto();
        userDto.setName(users.getName());
        userDto.setLast_name(users.getLast_name());
        userDto.setEmail(users.getEmail());
        return userDto;
    }

}
