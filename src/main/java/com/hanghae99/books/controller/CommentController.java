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

    private final CommentService commentService;

    @GetMapping("/api/books/{book_id}/comments")
    public List<Comment> ReadComment(@PathVariable Long book_id){
        return commentService.ReadComment(book_id);
    }

    @PostMapping("/api/books/{book_id}/comments")
    public ResponseEntity CreateComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long book_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.CreateComment(requestDto, book_id, userDetails.getId());
        if (comment == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/books/{book_id}/comments/{comment_id}")
    public ResponseEntity UpdateComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long book_id, @PathVariable Long comment_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.UpdateComment(requestDto, comment_id, userDetails.getId());
        if (comment == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/books/{book_id}/comments/{comment_id}")
    public ResponseEntity DeleteComment(@PathVariable Long book_id, @PathVariable Long comment_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.DeleteComment(book_id, comment_id, userDetails.getId());
        if (comment == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();

    }
}
