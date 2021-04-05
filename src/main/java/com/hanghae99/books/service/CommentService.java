package com.hanghae99.books.service;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.Book;
import com.hanghae99.books.domain.Comment;
import com.hanghae99.books.dto.CommentRequestDto;
import com.hanghae99.books.repository.AccountRepository;
import com.hanghae99.books.repository.BookRepository;
import com.hanghae99.books.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public List<Comment> ReadComment(Long books_id){
        List<Comment> comments = commentRepository.findByBookIdOrderByCreatedAtDesc(books_id);
        return comments;
    }

    public Comment CreateComment(CommentRequestDto requestDto, Long books_id, Long account_id){
        Book book = bookRepository.findById(books_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto);
        book.addComment(comment);
        account.addComment(comment);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public Comment UpdateComment(CommentRequestDto requestDto, Long comments_id, Long account_id) {
        Comment comment = commentRepository.findById(comments_id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if (!comment.getAccount().getId().equals(account_id)){
            return null;
        }else{
            comment.updateComment(requestDto);
        }
        return comment;
    }

    public Comment DeleteComment(Long books_id, Long comments_id, Long account_id){
        Book book = bookRepository.findById(books_id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(comments_id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (!comment.getAccount().getId().equals(account_id)){
            return null;
        }else{
            book.deleteComment(comment);
            account.deleteComment(comment);
            commentRepository.deleteById(comments_id);
            return comment;
        }

    }
}
