package com.hanghae99.books.controller;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.Heart;
import com.hanghae99.books.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/api/books/{book_id}/heart")
    public boolean ReadHeart(@PathVariable Long book_id, @AuthenticationPrincipal Account userDetails){
        return heartService.ReadHeart(book_id, userDetails.getId());
    }

    @PostMapping("/api/books/{book_id}/heart")
    public ResponseEntity CreateHeart(@PathVariable Long book_id, @AuthenticationPrincipal Account userDetails){
        Heart heart = heartService.CreateHeart(book_id, userDetails.getId());
        if (heart == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/books/{book_id}/heart")
    public ResponseEntity DeleteHeart(@PathVariable Long book_id, @AuthenticationPrincipal Account userDetails){
        Heart heart = heartService.DeleteHeart(book_id, userDetails.getId());
        if (heart == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
