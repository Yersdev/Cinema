package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.repo.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorService {

    private final DirectorRepo repo;

    @Autowired
    public DirectorService(DirectorRepo repo) {
        this.repo = repo;
    }

    // Получаем режиссера по ID
    public Director getDirector(int id) {
        Optional<Director> director = repo.findById(id);
        return director.orElse(null);
    }

    // Сохранение нового или обновление существующего режиссера
    public Director save(Director director) {
        return repo.save(director);
    }
}
