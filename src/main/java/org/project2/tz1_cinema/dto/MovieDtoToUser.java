package org.project2.tz1_cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Director;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDtoToUser {
    private String title;
    private String country;
    private String genre;
    private int release_data;
    private List<Actor> actors;
    private Director director;
}
