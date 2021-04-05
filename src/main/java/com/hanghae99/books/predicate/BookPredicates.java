package com.hanghae99.books.predicate;

import com.hanghae99.books.domain.Comment;
import com.hanghae99.books.domain.Heart;
import com.hanghae99.books.domain.QBook;
import com.querydsl.core.types.Predicate;

import java.util.Set;

public class BookPredicates {

    public static Predicate findByCommentAndHeart(Set<Comment> comments, Set<Heart> hearts) {
        return QBook.book.comments.any().in(comments).and(QBook.book.hearts.any().in(hearts));
    }
}
