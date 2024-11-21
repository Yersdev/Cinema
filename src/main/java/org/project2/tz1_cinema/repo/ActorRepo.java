package org.project2.tz1_cinema.repo;

import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long> {
    List<Actor> findByMovies(Movie movie);  // Найти актеров по фильму
//    List<Movie> findByActor(Actor actor);
    Actor findById(int id);

    Actor findByFirstNameAndLastName(String actorName, String actorLastName);
}