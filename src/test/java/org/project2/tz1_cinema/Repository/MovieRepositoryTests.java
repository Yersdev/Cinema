package org.project2.tz1_cinema.Repository;

import org.junit.jupiter.api.Test;
import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepo movieRepo;  // Change to field injection

    @Test
    public void MovieRepository_Save() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");

        Movie savedMovie = movieRepo.save(movie);


        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getId()).isGreaterThan(0);
    }
    @Test
    public void MovieRepository_FindByTitle() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");
        movieRepo.save(movie);
        String title = "Test1";
        Optional<Movie> movieList = movieRepo.findByTitle(title);
        assertThat(movieList.isPresent()).isTrue();
    }
    @Test
    public void MovieRepository_FindByCountry() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");
        movieRepo.save(movie);
        String country = "USA";
        Optional<Movie> movieList = movieRepo.findByCountry(country);
        assertThat(movieList.isPresent()).isTrue();
    }
    @Test
    public void MovieRepository_Delete() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");
        movieRepo.save(movie);
        movieRepo.delete(movie);
        assertThat(movieRepo.findAll()).isEmpty();
    }
}
