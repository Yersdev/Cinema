package org.project2.tz1_cinema.Repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.repo.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@Slf4j
public class ActorRepositoryTests {

    @Autowired
    private ActorRepo actorRepo;

    @Test
    public void ActorRepository_saveActor() {
        Actor actor = new Actor();
        actor.setFirstName("John");
        actor.setLastName("Doe");
        actor.setYearOfBirth(1901);
        actorRepo.save(actor);
        Assertions.assertNotNull(actorRepo.findById(actor.getId()));
    }

    @Test
    public void ActorRepository_findById() {
        // Подготовка данных
        int id = 1;
        Actor actor1 = new Actor();
        actor1.setFirstName("Sample Actor");
        actor1.setLastName("Sample Actor");
        actor1.setYearOfBirth(1901);
        actorRepo.save(actor1);
        log.info("Actor 1: " + actor1);

        // Выполнение теста
        Assertions.assertNotNull(actorRepo.findById(id));
    }
    @Test
    public void ActorRepository_findByFirstNameAndLastName() {
        String firstName = "John";
        String lastName = "Doe";
        Actor actor1 = new Actor();
        actor1.setFirstName(firstName);
        actor1.setLastName(lastName);
        actor1.setYearOfBirth(1901);
        actorRepo.save(actor1);
        Actor actor2 = actorRepo.findByFirstNameAndLastName(firstName, lastName);
        Assertions.assertNotNull(actorRepo.findById(actor2.getId()));
    }
    @Test
    public void ActorRepository_deleteActor() {
        int id = 1;
        Actor actor1 = new Actor();
        actor1.setId(id);
        actor1.setFirstName("John");
        actor1.setLastName("Doe");
        actor1.setYearOfBirth(1901);
        actorRepo.save(actor1);
        Actor actor2 = actorRepo.findById(id);
        actorRepo.delete(actor2);
        Assertions.assertNull(actorRepo.findById(actor2.getId()));
    }
}
