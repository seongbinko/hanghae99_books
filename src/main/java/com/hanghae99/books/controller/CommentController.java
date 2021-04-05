package com.hanghae99.books.controller;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.Book;
import com.hanghae99.books.domain.Comment;
import com.hanghae99.books.dto.CommentRequestDto;
import com.hanghae99.books.repository.BookRepository;
import com.hanghae99.books.repository.CommentRepository;
import com.hanghae99.books.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final CommentService commentService;

    @GetMapping("/api/books/{books_id}/comments")
    public List<Comment> ReadComment(@PathVariable Long books_id){
        return commentService.ReadComment(books_id);
    }

    @PostMapping("/api/books/{books_id}/comments")
    public ResponseEntity CreateComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long books_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.CreateComment(requestDto, books_id, userDetails.getId());
        if (comment == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/books/{books_id}/comments/{comments_id}")
    public ResponseEntity UpdateComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long books_id, @PathVariable Long comments_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.UpdateComment(requestDto, comments_id, userDetails.getId());
        if (comment == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/books/{books_id}/comments/{comments_id}")
    public ResponseEntity DeleteComment(@PathVariable Long books_id, @PathVariable Long comments_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.DeleteComment(books_id, comments_id, userDetails.getId());
        if (comment == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
        //ResponseEntity.badRequest().build();
    }
}
