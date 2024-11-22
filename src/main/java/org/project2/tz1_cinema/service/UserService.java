package org.project2.tz1_cinema.service;

import org.hibernate.annotations.Comments;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;
    private final CommentRepo commentRepo;

    public UserService(UserRepo userRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
    }
    public Users findByUsername(String username) {
        return userRepo.findUsersByName(username);
    }
    public Users findByEmail(String email) {
        return userRepo.findUsersByEmail(email);
    }

    @Transactional
    public void saveComment(Users user, Comment comment) {
        // Добавление комментария в список комментариев пользователя
        List<Comment> comments = user.getComments();
        comments.add(comment);  // Добавляем комментарий в список пользователя

        // Устанавливаем пользователя для комментария
        comment.setUsers(user);
        // Если необходимо, устанавливаем фильм для комментария
        // comment.setMovie(movie);

        // Сохраняем комментарий через репозиторий
        commentRepo.save(comment);

        // Также можно сохранить обновленный пользовательский объект (если это необходимо)
        userRepo.save(user);  // Если вы хотите, чтобы изменения были зафиксированы (не обязательно)
    }

    public List<Comment> getComments(Users user) {
        return user.getComments();
    }

}
