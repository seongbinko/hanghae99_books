package com.hanghae99.books.repository;

import com.hanghae99.books.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
