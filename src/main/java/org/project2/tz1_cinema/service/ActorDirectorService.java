package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.dto.actor_Dto;
import org.project2.tz1_cinema.dto.director_details_Dto;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.project2.tz1_cinema.repo.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorDirectorService {

    private final ActorRepo actorRepository;
    private final DirectorRepo directorRepository;

    @Autowired
    public ActorDirectorService(ActorRepo actorRepository, DirectorRepo directorRepository) {
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
    }

    public void addActor(actor_Dto actorDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setYearOfBirth(actorDto.getYearOfBirth());

        actorRepository.save(actor);
    }

//    public void addDirector(Director directo) {
//        Director director = new Director();
//        director.setFirstName(directorDto.getFirstName());
//        director.setLastName(directorDto.getLastName());
//        director.setYearOfBirth(directorDto.getYearOfBirth());
//
//        directorRepository.save(director);
//    }
}
