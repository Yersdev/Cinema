package org.project2.tz1_cinema.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class commentToFilm_Dto {
    private String comment;
    private String film_name;
    private UserDto user;
}
