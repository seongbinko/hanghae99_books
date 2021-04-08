package com.hanghae99.books.controller;

import com.hanghae99.books.config.UserDetailsImpl;
import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.Heart;
import com.hanghae99.books.domain.Message;
import com.hanghae99.books.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/api/books/{book_id}/heart")
    public HashMap<String, Object> ReadHeart(@PathVariable Long book_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return heartService.ReadHeart(book_id, userDetails.getAccount().getId());
    }

    @PostMapping("/api/books/{book_id}/heart")
    public ResponseEntity CreateHeart(@PathVariable Long book_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Heart heart = heartService.CreateHeart(book_id, userDetails.getAccount().getId());
        if (heart == null){
            Message message = Message.builder()
                    .message1("이미 좋아요 상태입니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/books/{book_id}/heart")
    public ResponseEntity DeleteHeart(@PathVariable Long book_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Heart heart = heartService.DeleteHeart(book_id, userDetails.getAccount().getId());
        if (heart == null){
            Message message = Message.builder()
                    .message1("취소할 좋아요가 없습니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

}
