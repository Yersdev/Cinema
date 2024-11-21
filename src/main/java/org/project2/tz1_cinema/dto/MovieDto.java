package org.project2.tz1_cinema.dto;

import lombok.*;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Director;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDto {
    private String title;
    private String country;
    private String genre;
    private int releaseYear;
    private List<ActorAddDto> actors;
    private DirectorAddDto director;
}
