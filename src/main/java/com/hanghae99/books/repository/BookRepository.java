package com.hanghae99.books.repository;

import com.hanghae99.books.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book,Long>, QuerydslPredicateExecutor<Book> {
    Page<Book> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = "select A.* , B.average " +
            "from book as A " +
            "left outer join (select book_id, round(avg(star_rate),1) as average from comment group by book_id) as B " +
            "on A.id = B.book_id order by average is null asc, average desc, A.created_at desc " +
            "limit :start, :offset"
            ,nativeQuery = true)
    List<Book> findAllByOrderByStarRate(int start, int offset);

    @Query(value = "select A.*, B.total " +
            "from book as A " +
            "left outer join(select book_id, sum(IF(is_heart = true, 1, 0)) as total from heart group by book_id) as B " +
            "on A.id = B.book_id order by total is null asc, total desc, A.created_at desc " +
            "limit :start, :offset"
            ,nativeQuery = true)
    List<Book> findAllByOrderByHeart(int start, int offset);
}
