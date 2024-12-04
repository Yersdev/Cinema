package org.project2.tz1_cinema.repo;

import org.project2.tz1_cinema.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findUsersByName(String username);
    Users findUsersByEmail(String email);
    boolean existsByName(String name);
    Optional<Users> findByEmail(String email);

}
