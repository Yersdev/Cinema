package org.project2.tz1_cinema.repo;

import org.project2.tz1_cinema.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
