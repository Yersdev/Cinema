package org.project2.tz1_cinema.repo;

import org.project2.tz1_cinema.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findUsersByName(String username);
    Users findUsersByEmail(String email);
}
