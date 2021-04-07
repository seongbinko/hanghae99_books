package com.hanghae99.books.repository;

import com.hanghae99.books.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {



    Comment findByAccountIdAndBookId(Long account_id, Long books_id);

    List<Comment> findByBookId(Long books_id);

    List<Comment> findByBookIdOrderByCreatedAtDesc(Long book_id);
}
