package org.project2.tz1_cinema.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.*;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.project2.tz1_cinema.service.MovieService;
import org.project2.tz1_cinema.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final MovieRepo movieRepo;
    private final MovieService movieService;
    private final ActorRepo actorRepo;
    private final UserRepo userRepo;
    private final CommentRepo commentRepo;
    private final UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<List<Comment>> getUserComments(@PathVariable int id) {
        Users user = userRepo.findById(id).orElse(null); // Используем Optional для удобства
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Возвращаем 404, если пользователь не найден
        }
       userService.getComments(user);
//
//        comment_details_dto userCommentsDto = new comment_details_dto(user.getName(), user.getLast_name(), user.getEmail());
//
//        List<Comment> comments = user.getComments();
//
//        for (Comment comment : comments) {
//            String movieTitle = comment.getMovie().getTitle();
//            String userComment = comment.getComment();
//            userCommentsDto.setComments(userComment);
//        }

        return new ResponseEntity<>(userService.getComments(user), HttpStatus.OK);
    }

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

//
//        @PostMapping("/add")
//        public ResponseEntity<String> addReview(@RequestBody ReviewDto reviewDto, @RequestHeader("Authorization") String token) {
//
//            if (token == null || !userService.isValidToken(token)) {
//                return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
//            }
//
//
//            if (reviewDto.getRating() < 1 || reviewDto.getRating() > 5) {
//                return new ResponseEntity<>("Rating must be between 1 and 5", HttpStatus.BAD_REQUEST);
//            }
//
//
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
