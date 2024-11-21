package org.project2.tz1_cinema.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project2.tz1_cinema.model.Movie;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class actor_details_Dto {
    @NotEmpty(message = "Please, the name could not be empty")
    @Size(min = 3, max = 50, message = "Please, the name should be between 3 - 50 characters")
    private String firstName;
    private String lastName;
    private List<Movie> movies;
    @NotEmpty(message = "Please, the name could not be empty")
    private int yearOfBirth;
    private List<Movie> filmedMovie;
}
