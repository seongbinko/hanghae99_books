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

    public void CreateComment(CommentRequestDto requestDto, Long books_id, Long account_id){
        Book book = bookRepository.findById(books_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto);
        comment.setBook(book);
        comment.setAccount(account);
        commentRepository.save(comment);
    }

    @Transactional
    public void UpdateComment(CommentRequestDto requestDto, Long comments_id, Long account_id) {
        Comment comment = commentRepository.findById(comments_id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if (!comment.getAccount().getId().equals(account_id)){
            System.out.println("게시글을 수정할 수 없습니다.");
        }else{
            comment.updateComment(requestDto);
        }


    }


    public void DeleteComment(Long comments_id, Long account_id){
        Comment comment = commentRepository.findById(comments_id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if (!comment.getAccount().getId().equals(account_id)){
            System.out.println("게시글을 수정할 수 없습니다.");
        }else{
            comment.setBook(null);
            comment.setAccount(null);
            commentRepository.deleteById(comments_id);
        }

    }
}
