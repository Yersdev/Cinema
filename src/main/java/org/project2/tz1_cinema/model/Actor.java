package org.project2.tz1_cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_seq")
    @SequenceGenerator(name = "actor_seq", sequenceName = "actor_sequence", allocationSize = 1)
    private int id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50)
    @NotEmpty
    private String lastName;
    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private List<Movie> movies;

    @Column()
    @Min(value = 1900, message = "Year of birth must be greater than or equal to 1900")
    @Max(value = 2023, message = "Year of birth must be less than or equal to 2023")

    private int yearOfBirth;
}
