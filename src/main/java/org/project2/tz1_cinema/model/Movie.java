package org.project2.tz1_cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "film")
@JsonIgnoreProperties
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_actors",  // промежуточная таблица для связи
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonBackReference
    private List<Actor> actors;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "director_id")
    private Director director;
    @Column(name = "release_year")
    private int releaseYear;
    private String country;
    private String genre;
    @JsonManagedReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST)  // связь с комментариями
    private List<Comment> comments;  // список комментариев к фильму

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }
}
