package org.project2.tz1_cinema.service;

import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ActorService {
    public final ActorRepo actorRepo;
    public ActorService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }


    public Actor getActor(int id) {
        log.info("Get actor with id {}", id);
        return actorRepo.findById(id);
    }
    public void save(Actor actor) {
        log.info("Save actor {}", actor);
        actorRepo.save(actor);
    }
    public Actor ActorIsHere(String actorName, String actorLastName) {
        return actorRepo.findByFirstNameAndLastName(actorName, actorLastName);
    }
}
