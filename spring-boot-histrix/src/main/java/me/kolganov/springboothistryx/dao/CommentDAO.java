package me.kolganov.springboothistryx.dao;

import me.kolganov.springboothistryx.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookId(long bookId);
}
