package org.project2.tz1_cinema.repo;

import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Override
    List<Movie> findAll();

    default List<Movie> findByRelease_data(int releaseData) {
        return null;
    }
    List<Movie> findByActors(Actor actor);


    List<Movie> getMovieByReleaseYear(int releaseYear);
}
