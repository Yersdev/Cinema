package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.dto.RegisterDTO;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Role;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.CommentRepo;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;
    private final CommentRepo commentRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users registerUser(RegisterDTO userRegistrationDTO) {
        Users user = new Users();
        user.setName(userRegistrationDTO.getName());
        user.setLast_name(userRegistrationDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(Role.USER); // По умолчанию присваиваем роль USER

        return userRepo.save(user);
    }
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

    public List<Comment> getComments(Users user) {
        return user.getComments();
    }

}
