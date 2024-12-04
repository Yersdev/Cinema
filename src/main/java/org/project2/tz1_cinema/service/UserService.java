package org.project2.tz1_cinema.service;

import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.dto.UserInfoUserDetails;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        List<Comment> comments = user.getComments();
        comments.add(comment);

        comment.setUsers(user);
        commentRepo.save(comment);

        userRepo.save(user);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Используем email вместо имени для поиска пользователя
        Optional<Users> userInfo = userRepo.findByEmail(username);
        log.info("user info: {}", userInfo);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
    public List<Comment> getComments(Users user) {
        return user.getComments();
    }

}
