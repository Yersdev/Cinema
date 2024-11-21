package org.project2.tz1_cinema.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class movie_Dto {
    private String title;
    private String country;
    private String genre;
    private int releaseYear;
    private List<actor_Dto> actors;
    private director_Dto director;
}
