package com.hanghae99.books.repository;

import com.hanghae99.books.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Heart findByBookIdAndAccountId(Long book_id, Long account_id);

    List<Heart> findByBookId(Long book_id);
}
