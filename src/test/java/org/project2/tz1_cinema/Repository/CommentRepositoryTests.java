package org.project2.tz1_cinema.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTests {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Test
    public void CommentRepository_CommentSave() {
        // Сохраняем объект Movie в базе данных
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepo.save(movie);

        // Создаем и сохраняем комментарий
        Comment comment = new Comment();
        comment.setComment("Test1");
        comment.setMovie(movie);
        Comment comment1 = commentRepo.save(comment);

        // Проверяем, что комментарий сохранен
        Assertions.assertNotNull(comment1);
    }

    @Test void CommentRepository_CommentDelete() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepo.save(movie);

        // Создаем и сохраняем комментарий
        Comment comment = new Comment();
        comment.setComment("Test1");
        comment.setMovie(movie);
        commentRepo.save(comment);
        commentRepo.delete(comment);
    }
}
