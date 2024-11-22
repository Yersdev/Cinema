package org.project2.tz1_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.transaction.annotation.Transactional;

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
        // Проверка на существование фильма
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with ID " + movieId + " not found"));

        // Проверка на существование пользователя
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        // Создание нового комментария
        Comment newComment = new Comment();
        newComment.setComment(content);
        newComment.setMovie(movie);
        newComment.setUsers(user);

        // Сохранение комментария
        return commentRepo.save(newComment);
    }

}
