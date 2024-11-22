package org.project2.tz1_cinema.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class comment_details_dto {
    private String movie_title;
    private String firstName;
    private String lastName;
    private String email;
    private String comments;

    public comment_details_dto(String firsName, String lastName, String email) {
        this.firstName = firsName;
        this.lastName = lastName;
        this.email = email;
    }


}
