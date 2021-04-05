package com.hanghae99.books.repository;

import com.hanghae99.books.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book,Long>, QuerydslPredicateExecutor<Book> {
    Page<Book> findAllByOrderByCreatedAtDesc(Pageable pageable);


//    @Query(value = "select A.* , B.average " +
//                    "from book as A " +
//                    "left outer join (select book_id, round(avg(star_rate),1) as average from comment group by book_id) as B " +
//                    "on A.id = B.book_id order by average is null asc, average desc, A.created_at desc"
//            ,countQuery = "select count(*) from book"
//            ,nativeQuery = true)
//    Page<Book> findAllPage(Pageable pageable);


    @Query(value = "select A.* , B.average " +
                "from book as A " +
                "left outer join (select book_id, round(avg(star_rate),1) as average from comment group by book_id) as B " +
                "on A.id = B.book_id order by average is null asc, average desc, A.created_at desc"
            ,countQuery = "select count(*) from book"
            ,nativeQuery = true)
    Page<Book> findAllByOrderByStarRate(Pageable pageable);
}
