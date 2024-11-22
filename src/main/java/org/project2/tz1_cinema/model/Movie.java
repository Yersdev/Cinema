package org.project2.tz1_cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "film")
@JsonIgnoreProperties
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150)
    @Size(max = 150, message = "The title could not have more than 150 characters")
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
    private Integer releaseYear;
    private String country;
    private String genre;
    @JsonManagedReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST)  // связь с комментариями
    private List<Comment> comments;  // список комментариев к фильму

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }
}
