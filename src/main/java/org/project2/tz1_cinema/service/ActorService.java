package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    public final ActorRepo actorRepo;
    public ActorService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }
//    public List<Movie> findByActors(Actor actor) {
//        return actorRepo.findByActor(actor);
//    }
    public Actor getActor(int id) {
        return actorRepo.findById(id);
    }
    public void save(Actor actor) {
        actorRepo.save(actor);
    }
    public Actor ActorIsHere(String actorName, String actorLastName) {
        return actorRepo.findByFirstNameAndLastName(actorName, actorLastName);
    }
}