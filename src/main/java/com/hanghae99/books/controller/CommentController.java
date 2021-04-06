package com.hanghae99.books.controller;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.Book;
import com.hanghae99.books.domain.Comment;
import com.hanghae99.books.domain.Message;
import com.hanghae99.books.dto.CommentRequestDto;
import com.hanghae99.books.repository.BookRepository;
import com.hanghae99.books.repository.CommentRepository;
import com.hanghae99.books.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/books/{book_id}/comments")
    public HashMap<String, Object> ReadComment(@PathVariable Long book_id){
        return commentService.ReadComment(book_id);
    }

    @PostMapping("/api/books/{book_id}/comments")
    public ResponseEntity CreateComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long book_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.CreateComment(requestDto, book_id, userDetails.getId());
        if (comment == null){
            Message message = Message.builder()
                    .message1("댓글은 한 책에 한번만 작성가능합니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/books/{book_id}/comments/{comment_id}")
    public ResponseEntity UpdateComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long book_id, @PathVariable Long comment_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.UpdateComment(requestDto, comment_id, userDetails.getId());
        if (comment == null){
            Message message = Message.builder()
                    .message1("직접 작성한 댓글만 수정할 수 있습니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/books/{book_id}/comments/{comment_id}")
    public ResponseEntity DeleteComment(@PathVariable Long book_id, @PathVariable Long comment_id, @AuthenticationPrincipal Account userDetails){
        Comment comment = commentService.DeleteComment(book_id, comment_id, userDetails.getId());
        if (comment == null){
            Message message = Message.builder()
                    .message1("직접 작성한 댓글만 삭제할 수 있습니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();

    }
}
