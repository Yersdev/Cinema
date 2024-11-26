package org.project2.tz1_cinema.service;

import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.repo.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DirectorService {

    private final DirectorRepo repo;

    @Autowired
    public DirectorService(DirectorRepo repo) {
        this.repo = repo;
    }

    public Director getDirector(int id) {
        Optional<Director> director = repo.findById(id);
        return director.orElse(null);
    }

    public Director save(Director director) {
        log.info("save director: {}", director);
        return repo.save(director);
    }
}
