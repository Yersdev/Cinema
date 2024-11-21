package org.project2.tz1_cinema.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class MovieAddDto {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    private Integer releaseYear;

    @NotEmpty
    private String genre;

    private String country;

    private List<ActorAddDto> actors;  // Список актеров

    private Director director;  // Директор фильма

}
