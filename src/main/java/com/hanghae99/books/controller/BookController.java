package com.hanghae99.books.controller;


import com.hanghae99.books.domain.Book;
import com.hanghae99.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return  books;
    }
}
