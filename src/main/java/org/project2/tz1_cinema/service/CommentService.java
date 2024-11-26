package org.project2.tz1_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class CommentService {
    private CommentRepo commentRepo;
    private final MovieRepo movieRepo;
    private final UserRepo userRepo;
    public CommentService(MovieRepo movieRepo, UserRepo userRepo) {
        this.movieRepo = movieRepo;
        this.userRepo = userRepo;
    }

    @Transactional

    public Comment createComment(String content, Integer movieId, Integer userId) {
    log.info("Comment created with content:" + content, movieId, userId);
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with ID " + movieId + " not found"));

        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        Comment newComment = new Comment();
        newComment.setComment(content);
        newComment.setMovie(movie);
        newComment.setUsers(user);

        return commentRepo.save(newComment);
    }

}
