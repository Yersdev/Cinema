package org.project2.tz1_cinema.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project2.tz1_cinema.model.Director;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class director_details_Dto {

    private Integer id;


    @NotEmpty(message = "Please, the name could not be empty")
    @Size(min = 3, max = 50, message = "Please, the name should be between 3 - 50 characters")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Year of birth cannot be empty")
    private Integer yearOfBirth;

    private Director director;

    public director_details_Dto(int id, String firstName, String lastName, Integer yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public @NotEmpty(message = "Year of birth cannot be empty") Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(@NotEmpty(message = "Year of birth cannot be empty") Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
