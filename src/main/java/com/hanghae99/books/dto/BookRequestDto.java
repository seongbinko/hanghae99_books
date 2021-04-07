package com.hanghae99.books.dto;

import com.hanghae99.books.domain.Book;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class BookRequestDto {
    private String imgUrl;
    private String title;

    public BookRequestDto(Book book) {
        this.imgUrl = book.getImgUrl();
        this.title = book.getTitle();
    }
}
