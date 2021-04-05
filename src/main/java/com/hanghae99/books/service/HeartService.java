package com.hanghae99.books.service;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.Book;
import com.hanghae99.books.domain.Heart;
import com.hanghae99.books.repository.AccountRepository;
import com.hanghae99.books.repository.BookRepository;
import com.hanghae99.books.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;

    public boolean ReadHeart(Long book_id, Long account_id) {
        Heart heart = heartRepository.findByBookIdAndAccountId(book_id, account_id);
        if (heart == null){
            return false;
        }
        return heart.isHeart();
    }

    @Transactional
    public Heart CreateHeart(Long book_id, Long account_id){
        Book book = bookRepository.findById(book_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBookIdAndAccountId(book_id, account_id);
        if (heart == null) {
            Heart newheart = new Heart();
            newheart.setHeart(true);
            book.addHeart(newheart);
            account.addHeart(newheart);
            heartRepository.save(newheart);
            return newheart;
        }
//        }else if (!heart.isHeart()){
//            heart.setHeart(true);
//            return heart.isHeart();
//
//        }else{
//            heart.setHeart(false);
//            return heart.isHeart();
//        }
        return null;
    }

    public Heart DeleteHeart(Long book_id, Long account_id){
        Book book = bookRepository.findById(book_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBookIdAndAccountId(book_id, account_id);
        if (heart != null){
            book.deleteHeart(heart);
            account.deleteHeart(heart);
            heartRepository.deleteById(heart.getId());
            return heart;
        }
        return null;
    }
}
