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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;


    public HashMap<String, Object> ReadComment(Long book_id){
        float totalStarRate = 0;
        float starRate;
        float avgStarRate;
        List<Comment> comments = commentRepository.findByBookIdOrderByCreatedAtDesc(book_id);
        List<Comment> starRateCountList = commentRepository.findByBookId(book_id);

        HashMap<String, Object> map = new HashMap<>();
        Integer starRateCount = starRateCountList.size();
        for(int i=0; i<starRateCountList.size(); i++){
            totalStarRate += starRateCountList.get(i).getStarRate();
            System.out.println(totalStarRate);
        }
        starRate = totalStarRate / starRateCount;
        System.out.println(starRate);
        avgStarRate= Float.parseFloat(String.format("%.1f", starRate));
        map.put("comment", comments);
        map.put("avgStarRate", avgStarRate);
        map.put("starRateCount", starRateCount);

        return map;
    }

    public Comment CreateComment(CommentRequestDto requestDto, Long book_id, Long account_id){
        Book book = bookRepository.findById(book_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Comment checkComment = commentRepository.findByAccountIdAndBookId(account_id, book_id);

        if (checkComment == null){
            Comment comment = new Comment(requestDto);
            book.addComment(comment);
            account.addComment(comment);
            commentRepository.save(comment);

            return comment;
        }else{
            return null;
        }

    }

    @Transactional
    public Comment UpdateComment(CommentRequestDto requestDto, Long comment_id, Long account_id) {
        Comment comment = commentRepository.findById(comment_id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if (!comment.getAccount().getId().equals(account_id)){
            return null;
        }else{
            comment.updateComment(requestDto);
        }
        return comment;
    }

    public Comment DeleteComment(Long book_id, Long comment_id, Long account_id){
        Book book = bookRepository.findById(book_id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(comment_id).orElseThrow(
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
            commentRepository.deleteById(comment_id);
            return comment;
        }

    }
}
