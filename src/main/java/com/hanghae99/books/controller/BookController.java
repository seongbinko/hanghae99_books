package com.hanghae99.books.controller;


import com.hanghae99.books.domain.Book;
import com.hanghae99.books.dto.BookRequestDto;
import com.hanghae99.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;


    @GetMapping("/api/books")
    public Page<BookRequestDto> getAllBooks(
            @RequestParam("page") int page, // 요청페이지
            @RequestParam("size") int size, // 요청 사이즈 (게시글에 몇개씩 보여줄지)
            @RequestParam("sort") String sort // 정렬 기준 최신순, 좋아요순, 평점순
    ) {
        // Sort sortBy = Sort.by(Sort.Direction.DESC, sort);
        Pageable pageable = PageRequest.of(page-1, size);
        List<BookRequestDto> bookListDto = new ArrayList<>();
        if(sort.equals("starRate")) {
            int start = (page-1) * size;
            List<Book> bookList  = bookRepository.findAllByOrderByStarRate(start, size);

            //return new PageImpl<>(bookList, pageable, bookList.size());
        }

        else if(sort.equals("heart")) {
            int start = (page-1) * size;
            List<Book> bookList  = bookRepository.findAllByOrderByHeart(start, size);
            for(int i=0; i<bookList.size(); i++){
                Book book = bookList.get(i);
                BookRequestDto requestDto = new BookRequestDto(book);
                bookListDto.add(requestDto);
            }
            return new PageImpl<>(bookList, pageable, bookList.size());
        }
        else{
            Page<Book> books = bookRepository.findAllByOrderByCreatedAtDesc(pageable);
            for(int i=0; i<books.getSize(); i++){
                Book book = books.getContent().get(i);
                BookRequestDto requestDto = new BookRequestDto(book);
                bookListDto.add(requestDto);
            }
            return new PageImpl<>(bookList, pageable, bookList.size());
        }

        return null;
    }

    @GetMapping("/api/books/{book_id}")
    public Book getBookById(@PathVariable Long book_id) {
        Book book = bookRepository.findById(book_id).orElse(null);
        return book;
    }

}
