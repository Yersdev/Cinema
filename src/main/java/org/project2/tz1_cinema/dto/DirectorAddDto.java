package org.project2.tz1_cinema.dto;

import lombok.*;
import org.project2.tz1_cinema.model.Director;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DirectorAddDto {
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;

}
