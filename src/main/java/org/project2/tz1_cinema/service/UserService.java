package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public Users findByUsername(String username) {
        return userRepo.findUsersByName(username);
    }
    public Users findByEmail(String email) {
        return userRepo.findUsersByEmail(email);
    }

}
